package pers.yhf.seckill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.yhf.seckill.domain.SeckillGoods;
import pers.yhf.seckill.vo.GoodsVo;
 
 
public interface GoodsMapper { 
	 
	public List<GoodsVo> getGoodsVoList();
  
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);
 
	public int reduceStock(SeckillGoods g);  
	
	public int selectStock(@Param("goodsId")long goodsId);   

}
