package xyz.fjzkuroko.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fjzkuroko.seckill.service.IUserService;
import xyz.fjzkuroko.seckill.vo.LoginVo;
import xyz.fjzkuroko.seckill.vo.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/9 21:07
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录功能
     * @param loginVo 接收登录参数
     * @return ResponseBean
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public ResponseBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", loginVo);
        return userService.doLogin(loginVo, request, response);
    }

}
