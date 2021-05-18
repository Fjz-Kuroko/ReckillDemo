package xyz.fjzkuroko.seckill.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.service.IOrderService;
import xyz.fjzkuroko.seckill.service.ISecOrderService;
import xyz.fjzkuroko.seckill.vo.OrderDetailVo;
import xyz.fjzkuroko.seckill.vo.ResponseBean;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
@Controller
@RequestMapping("/secOrder")
public class SecOrderController {
    @Autowired
    private ISecOrderService secOrderService;

    /**
     * 订单详情
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseBean detail(User user, Long orderId) {
        if (user == null) {
            return ResponseBean.error(ResponseBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo orderDetailVo = secOrderService.detail(orderId);
        return ResponseBean.success(orderDetailVo);
    }
}
