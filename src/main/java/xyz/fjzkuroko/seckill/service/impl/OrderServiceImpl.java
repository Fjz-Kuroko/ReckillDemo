package xyz.fjzkuroko.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.fjzkuroko.seckill.exception.GlobalException;
import xyz.fjzkuroko.seckill.mapper.OrderMapper;
import xyz.fjzkuroko.seckill.pojo.Order;
import xyz.fjzkuroko.seckill.service.IOrderService;
import xyz.fjzkuroko.seckill.vo.OrderDetailVo;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
