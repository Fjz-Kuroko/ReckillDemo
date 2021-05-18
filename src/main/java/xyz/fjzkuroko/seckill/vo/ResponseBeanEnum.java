package xyz.fjzkuroko.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/9 21:21
 */
@Getter
@ToString
@AllArgsConstructor
public enum ResponseBeanEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务器异常"),
    // 登录模块5002xx
    LOGIN_ERROR(500210, "用户名或密码错误"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    BIND_ERROR(500212, "参数校验失败"),
    MOBILE_NOT_EXIST(500213, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214, "密码修改失败"),
    SESSION_ERROR(500215, "用户不存在"),
    // 秒杀模块5005xx
    EMPTY_STOCK(500500, "库存不足"),
    REPEATE_ERROR(500501, "该商品每人限购一件"),
    // 订单模块5003xx
    ORDER_NOT_EXIST(500300, "订单信息不存在"),
    ;

    private final Integer code;
    private final String message;
}
