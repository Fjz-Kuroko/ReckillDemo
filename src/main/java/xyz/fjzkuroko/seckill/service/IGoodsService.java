package xyz.fjzkuroko.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fjzkuroko.seckill.pojo.Goods;
import xyz.fjzkuroko.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> getGoodsVo();

    GoodsVo getGoodsVoById(Long goodsId);
}
