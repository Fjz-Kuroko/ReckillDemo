package xyz.fjzkuroko.seckill.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.fjzkuroko.seckill.utils.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/12 18:01
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("preHandle拦截的请求路径为：{}", requestURI);
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        log.info("userTicket:{}", ticket);
        if (!StringUtils.isEmpty(ticket)) {
            log.info("{}-拦截放行", requestURI);
            return true;
        }
        log.info("{}-拦截不放行", requestURI);
        request.setAttribute("msg", "未登录！");

        response.sendRedirect("/login/toLogin");
//        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }
}
