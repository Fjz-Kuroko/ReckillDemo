package xyz.fjzkuroko.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.fjzkuroko.seckill.interceptor.LoginInterceptor;

import java.util.List;

/**
 * MVC配置类
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/12 17:32
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 把静态资源目录注册进来
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // 拦截所有
                .excludePathPatterns("/", "/goods/toList", "/error/**", "/login/**", "/css/**", "/js/**", "/bootstrap/**",
                        "/images/**", "/favicon.ico", "/img/**", "/layer/**", "/jquery-validation/**"); // 放行静态资源
    }
}
