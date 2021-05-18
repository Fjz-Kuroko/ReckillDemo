package xyz.fjzkuroko.seckill.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/11 20:58
 */
public class ValidatorUtil {

    // 手机号码正则表达式
    private static final Pattern MOBILE_PATTERN = Pattern.compile("[1]([3-9])[0-9]{9}$");

    /**
     * 校验手机号是否正确
     * @param mobile 手机号
     * @return boolean
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher matcher = MOBILE_PATTERN.matcher(mobile);
        return matcher.matches();
    }

}
