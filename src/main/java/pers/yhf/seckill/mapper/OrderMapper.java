package pers.yhf.seckill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pers.yhf.seckill.domain.SeckillOrder;
import pers.yhf.seckill.domain.OrderInfo; 
 
@Mapper
public interface OrderMapper {
 
	public SeckillOrder getSeckillOrderByUserIdAndGoodsId(@Param("userId")Long userId, @Param("goodsId")long goodsId);
 
	public long insertOrderInfo(OrderInfo orderInfo);
 
	public void insertSeckillOrder(SeckillOrder seckillOrder);
 
	public OrderInfo getOrderById(@Param("orderId")long orderId);
 
	public void deleteSeckillOrderByGoodIdAndUserId(@Param("goodsId")long goodsId, @Param("userId")long userId);
 
	public List<SeckillOrder> getAllSeckillOrders();  
	
}
