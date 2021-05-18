package xyz.fjzkuroko.seckill.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import xyz.fjzkuroko.seckill.annotation.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/9 21:54
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32, max = 32)
    private String password;
}
