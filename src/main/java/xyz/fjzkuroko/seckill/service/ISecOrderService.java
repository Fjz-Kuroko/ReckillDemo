package xyz.fjzkuroko.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fjzkuroko.seckill.pojo.Order;
import xyz.fjzkuroko.seckill.pojo.SecOrder;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.vo.GoodsVo;
import xyz.fjzkuroko.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
public interface ISecOrderService extends IService<SecOrder> {

    Order seckill(User user, GoodsVo goods);

    OrderDetailVo detail(Long orderId);
}
