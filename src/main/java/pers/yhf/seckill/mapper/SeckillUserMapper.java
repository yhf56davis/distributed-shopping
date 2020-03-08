package pers.yhf.seckill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pers.yhf.seckill.domain.SeckillGoods;
import pers.yhf.seckill.domain.SeckillUser; 
 
@Mapper
public interface SeckillUserMapper {

	public SeckillUser getUserById(@Param("id")long id);
 
	public void update(SeckillUser toBeUpdate);

    public void updateSeckillUserLoginInfo(@Param("nickname")String nickname);
 
	public SeckillUser getSeckillUserByNickName(@Param("nickname")String nickname);

	public int getSeckillGoodsStockCountByGoodId(@Param("goodsId")long goodsId);
    
	public void backStockCount(SeckillGoods goods);

	public List<SeckillGoods> getSeckillGoodsList();

	public SeckillGoods getSeckillGoodsByGoodsId(@Param("goodsId")long goodsId);

	public List<SeckillGoods> getSeckillGoodsVoList();        

	 
	 

	 
	
}
