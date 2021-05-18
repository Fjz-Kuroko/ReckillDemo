package xyz.fjzkuroko.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.fjzkuroko.seckill.pojo.Order;

/**
 * 订单详情返回对象
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/17 22:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
    private Order order;
    private GoodsVo goodsVo;
}
