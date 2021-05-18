package xyz.fjzkuroko.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.vo.LoginVo;
import xyz.fjzkuroko.seckill.vo.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-09
 */
public interface IUserService extends IService<User> {

    ResponseBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

    ResponseBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
}
