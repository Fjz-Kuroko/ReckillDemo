package xyz.fjzkuroko.seckill.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/11 22:16
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
