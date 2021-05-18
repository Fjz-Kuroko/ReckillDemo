package xyz.fjzkuroko.seckill.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

/**
 * 全局异常
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/11 21:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private ResponseBeanEnum responseBeanEnum;
}
