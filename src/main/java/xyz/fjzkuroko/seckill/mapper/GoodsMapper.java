package xyz.fjzkuroko.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fjzkuroko.seckill.pojo.Goods;
import xyz.fjzkuroko.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 获取商品列表
     * @return  List<GoodsVo>
     */
    List<GoodsVo> getGoodsVo();

    GoodsVo getGoodsVoById(Long goodsId);
}
