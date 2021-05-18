package xyz.fjzkuroko.seckill.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.vo.ResponseBean;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-09
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户信息，用于测试
     * @param user User
     * @return ResponseBean
     */
    @RequestMapping("/info")
    @ResponseBody
    public ResponseBean info(User user) {
        return ResponseBean.success(user);
    }

}
