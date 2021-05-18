package xyz.fjzkuroko.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/9 21:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {

    private Integer code;
    private String message;
    private Object object;

    /**
     * 成功返回结果
     * @return ResponseBean
     */
    public static ResponseBean success() {
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(),
                ResponseBeanEnum.SUCCESS.getMessage(),
                null);
    }

    /**
     * 成功返回结果
     * @param object Object返回的对象
     * @return ResponseBean
     */
    public static ResponseBean success(Object object) {
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(),
                ResponseBeanEnum.SUCCESS.getMessage(),
                object);
    }

    /**
     * 失败返回结果
     * @param responseBeanEnum ResponseBeanEnum
     * @return ResponseBean
     */
    public static ResponseBean error(ResponseBeanEnum responseBeanEnum) {
        return new ResponseBean(responseBeanEnum.getCode(),
                responseBeanEnum.getMessage(),
                null);
    }

    /**
     * 失败返回的结果
     * @param responseBeanEnum ResponseBeanEnum
     * @param object Object返回的对象
     * @return ResponseBean
     */
    public static ResponseBean error(ResponseBeanEnum responseBeanEnum, Object object) {
        return new ResponseBean(responseBeanEnum.getCode(),
                responseBeanEnum.getMessage(),
                object);
    }

}
