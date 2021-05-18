package xyz.fjzkuroko.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fjzkuroko.seckill.exception.GlobalException;
import xyz.fjzkuroko.seckill.mapper.OrderMapper;
import xyz.fjzkuroko.seckill.mapper.SecOrderMapper;
import xyz.fjzkuroko.seckill.pojo.Order;
import xyz.fjzkuroko.seckill.pojo.SecGoods;
import xyz.fjzkuroko.seckill.pojo.SecOrder;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.service.IGoodsService;
import xyz.fjzkuroko.seckill.service.ISecGoodsService;
import xyz.fjzkuroko.seckill.service.ISecOrderService;
import xyz.fjzkuroko.seckill.vo.GoodsVo;
import xyz.fjzkuroko.seckill.vo.OrderDetailVo;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
@Service
public class SecOrderServiceImpl extends ServiceImpl<SecOrderMapper, SecOrder> implements ISecOrderService {

    @Autowired
    private ISecGoodsService secGoodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISecOrderService secOrderService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 执行秒杀
     * @param user 用户
     * @param goods 商品
     * @return Order
     */
    @Transactional
    @Override
    public Order seckill(User user, GoodsVo goods) {
        // 查询当前秒杀商品
        SecGoods secGoods = secGoodsService.getOne(new QueryWrapper<SecGoods>().eq("goods_id", goods.getId()));
        // 库存减一
        secGoods.setStockCount(secGoods.getStockCount() - 1);
//        secGoodsService.updateById(secGoods);
        // 通过sql语句进行更新会有一个行锁，有效解决超卖问题，通过建立索引解决同一用户多次秒杀问题
        boolean result = secGoodsService.update(new UpdateWrapper<SecGoods>().setSql("stock_count = stock_count - 1").eq("goods_id", goods.getId()).gt("stock_count", 0));
        if (!result) {
            return null;
        }
        // 生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        // 生成秒杀订单
        SecOrder secOrder = new SecOrder();
        secOrder.setUserId(user.getId());
        secOrder.setOrderId(order.getId());
        secOrder.setGoodsId(goods.getId());
        secOrderService.save(secOrder);
        // 把秒杀订单信息存进redis
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goods.getId(), secOrder);
        return order;
    }

    @Override
    public OrderDetailVo detail(Long orderId) {
        if (orderId == null) {
            throw new GlobalException(ResponseBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.getGoodsVoById(order.getGoodsId());
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrder(order);
        orderDetailVo.setGoodsVo(goodsVo);
        return orderDetailVo;
    }
}
