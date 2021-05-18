package xyz.fjzkuroko.seckill.validator;

import org.springframework.util.StringUtils;
import xyz.fjzkuroko.seckill.utils.ValidatorUtil;
import xyz.fjzkuroko.seckill.annotation.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 定义手机号码校验规则
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/11 21:28
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required) {
            if (StringUtils.isEmpty(value)) {
                return true;
            }
        }
        return ValidatorUtil.isMobile(value);
    }
}
