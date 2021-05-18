package xyz.fjzkuroko.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.fjzkuroko.seckill.pojo.User;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/17 20:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailVo {
    private User user;
    private GoodsVo goodsVo;
    private int secKillStatus;
    private int remainSeconds;
}
