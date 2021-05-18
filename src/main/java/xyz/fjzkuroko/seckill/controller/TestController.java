package xyz.fjzkuroko.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/9 10:56
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "fjzkuroko");
        return "hello";
    }

}
