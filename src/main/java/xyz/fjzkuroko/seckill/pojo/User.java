package xyz.fjzkuroko.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("seckill_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID,手机号码
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码,MD5(MD5(password明文+salt)+salt)
     */
    private String password;

    /**
     * MD5加密所用的盐
     */
    private String salt;

    /**
     * 头像
     */
    private String head;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    private Long loginCount;


}
