package xyz.fjzkuroko.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.fjzkuroko.seckill.mapper.GoodsMapper;
import xyz.fjzkuroko.seckill.pojo.Goods;
import xyz.fjzkuroko.seckill.service.IGoodsService;
import xyz.fjzkuroko.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fjzkuroko
 * @since 2021-05-13
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 获取商品列表数据
     * @return List<GoodsVo>
     */
    @Override
    public List<GoodsVo> getGoodsVo() {
        return goodsMapper.getGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoById(Long goodsId) {
        return goodsMapper.getGoodsVoById(goodsId);
    }

}
