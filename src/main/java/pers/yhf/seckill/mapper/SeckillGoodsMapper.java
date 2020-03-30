package pers.yhf.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SeckillGoodsMapper {

	public long getSeckillGoodsVersion(@Param("goodsId")long goodsId); 
 
}
