package xyz.fjzkuroko.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.fjzkuroko.seckill.pojo.Goods;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品返回对象
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/13 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
