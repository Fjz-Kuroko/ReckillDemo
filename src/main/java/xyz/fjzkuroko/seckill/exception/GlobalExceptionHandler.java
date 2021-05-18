package xyz.fjzkuroko.seckill.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fjzkuroko.seckill.vo.ResponseBean;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

/**
 * 全局异常处理类
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/11 21:48
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseBean exceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return ResponseBean.error(ex.getResponseBeanEnum());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            ResponseBean responseBean = ResponseBean.error(ResponseBeanEnum.BIND_ERROR);
            responseBean.setMessage("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return responseBean;
        }
        log.info("错误信息：{}", e.getMessage());
        return ResponseBean.error(ResponseBeanEnum.ERROR, e.getMessage());
    }

}
