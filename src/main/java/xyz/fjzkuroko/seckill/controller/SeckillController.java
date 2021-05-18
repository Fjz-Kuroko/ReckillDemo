package xyz.fjzkuroko.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fjzkuroko.seckill.pojo.Order;
import xyz.fjzkuroko.seckill.pojo.SecOrder;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.service.IGoodsService;
import xyz.fjzkuroko.seckill.service.ISecOrderService;
import xyz.fjzkuroko.seckill.vo.GoodsVo;
import xyz.fjzkuroko.seckill.vo.ResponseBean;
import xyz.fjzkuroko.seckill.vo.ResponseBeanEnum;

/**
 * 秒杀
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/13 22:29
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISecOrderService secOrderService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Windows 优化前QPS：504
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/doSeckill2")
    public String doSeckill2(Model model, User user, Long goodsId) {
        // 未登录直接去登录页面
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        // 判断库存是否足够
        if (goods.getStockCount() < 1) {
            model.addAttribute("errMsg", ResponseBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }

        // 查询当前用户和商品id的订单是否存在
        SecOrder secOrder = secOrderService.getOne(new QueryWrapper<SecOrder>().eq("user_id", user.getId())
                .eq("goods_id", goodsId));
        // 判断是否重复秒杀
        if (secOrder != null) {
            model.addAttribute("errMsg", ResponseBeanEnum.REPEATE_ERROR.getMessage());
            return "seckillFail";
        }

        // 执行秒杀
        Order order = secOrderService.seckill(user, goods);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }

    /**
     * Windows 优化后QPS：805
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean doSeckill(Model model, User user, Long goodsId) {
        // 未登录直接去登录页面
        if (user == null) {
            return ResponseBean.error(ResponseBeanEnum.SESSION_ERROR);
        }
        // TODO: 2021/5/18 把商品信息存进redis中，增加速度 
        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        // 判断库存是否足够
        if (goods.getStockCount() < 1) {
            model.addAttribute("errMsg", ResponseBeanEnum.EMPTY_STOCK.getMessage());
            return ResponseBean.error(ResponseBeanEnum.EMPTY_STOCK);
        }

        // 查询当前用户和商品id的订单是否存在
//        SecOrder secOrder = secOrderService.getOne(new QueryWrapper<SecOrder>().eq("user_id", user.getId())
//                .eq("goods_id", goodsId));
        // 从redis中查询订单是否存在
        SecOrder secOrder = (SecOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        // 判断是否重复秒杀
        if (secOrder != null) {
            model.addAttribute("errMsg", ResponseBeanEnum.REPEATE_ERROR.getMessage());
            return ResponseBean.error(ResponseBeanEnum.REPEATE_ERROR);
        }

        // 执行秒杀
        Order order = secOrderService.seckill(user, goods);
        return ResponseBean.success(order);
    }

}