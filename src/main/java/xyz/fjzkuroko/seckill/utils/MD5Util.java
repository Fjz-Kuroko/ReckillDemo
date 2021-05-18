package xyz.fjzkuroko.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/9 19:58
 */
public class MD5Util {

    private static final String SALT = "1a2b3c4d";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 前端加密
     * @param inputPass 输入的明文密码
     * @return 第一次加密后的密码
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    /**
     * 后端加密
     * @param formPass 提交到后端的密码
     * @param salt 盐
     * @return 二次加密后的密码
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 从直接明文变成存储到数据库的密文转换
     * @param inputPass 前端输入的明文
     * @param salt 盐
     * @return 两次加密后的密文
     */
    public static String inputPassToDBPass(String inputPass, String salt) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        //d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9", "1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }

}
