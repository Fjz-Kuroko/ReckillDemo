package xyz.fjzkuroko.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.fjzkuroko.seckill.exception.GlobalException;
import xyz.fjzkuroko.seckill.mapper.UserMapper;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.service.IUserService;
import xyz.fjzkuroko.seckill.utils.CookieUtil;
import xyz.fjzkuroko.seckill.utils.MD5Util;
import xyz.fjzkuroko.seckill.utils.UUIDUtil;
import xyz.fjzkuroko.seckill.utils.ValidatorUtil;
import xyz.fjzkuroko.seckill.vo.LoginVo;
import xyz.fjzkuroko.seckill.vo.ResponseBean;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-09
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录
     * @param loginVo
     * @return ResponseBean
     */
    @Override
    public ResponseBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        // 根据手机号查找用户
        User user = userMapper.selectById(mobile);
        if (user == null) {
            // 抛出自定义全局异常
            throw new GlobalException((ResponseBeanEnum.LOGIN_ERROR));
        }
        // 判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException((ResponseBeanEnum.LOGIN_ERROR));
        }
        // 生成Cookie
        String ticket = UUIDUtil.uuid();
//        request.getSession().setAttribute(ticket, user);  // 把用户信息放在session

        try {
            // 将用户信息存入redis
            redisTemplate.opsForValue().set("user:" + ticket, user);
        } catch (Exception e) {
            log.info("用户存进Redis中发送异常：{}", e.getMessage());
            return ResponseBean.error(ResponseBeanEnum.ERROR);
        }
        try {
            CookieUtil.setCookie(request, response, "userTicket", ticket);
            log.info("Cookie中写入userTicket:{}", ticket);
        } catch (Exception e) {
            log.info("把Ticket存进Cookie中发送异常：{}", e.getMessage());
            return ResponseBean.error(ResponseBeanEnum.ERROR);
        }
        return ResponseBean.success(ticket);
    }

    /**
     * 根据Cookie获取用户
     * @param userTicket
     * @return
     */
    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            // 重新设置Cookie
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    /**
     * 更新密码
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public ResponseBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) {
            throw new GlobalException(ResponseBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDBPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if (result == 1) {
            redisTemplate.delete("user:" + userTicket);
            return ResponseBean.success();
        }
        return ResponseBean.error(ResponseBeanEnum.PASSWORD_UPDATE_FAIL);
    }
}
