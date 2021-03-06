package pers.yhf.seckill.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import pers.yhf.seckill.domain.SeckillOrder;
import pers.yhf.seckill.domain.SeckillUser;
import pers.yhf.seckill.domain.OrderInfo;
import pers.yhf.seckill.mapper.OrderMapper;
import pers.yhf.seckill.redisCluster.RedisService;
import pers.yhf.seckill.redisCluster.SecKillActivityKey;
import pers.yhf.seckill.util.TimeUtil;
import pers.yhf.seckill.vo.GoodsVo;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private RedisService redisService;
	
	public SeckillOrder getSeckillOrderByUserIdAndGoodsId(Long userId, long goodsId) {
	    // return this.orderDao.getMiaoshaOrderByUserIdAndGoodsId(userId,goodsId);
		return redisService.get(SecKillActivityKey.getSecKillOrderByUidGid, ""+userId+"_"+goodsId, SeckillOrder.class);
	}
 
	
	
	@Transactional
	public OrderInfo createOrder(SeckillUser user, GoodsVo goods) { 
		
		String orderId = TimeUtil.getRandomTimeId();
		
		OrderInfo orderInfo = new OrderInfo();
		
		orderInfo.setOrderId(orderId); 
		orderInfo.setCreateDate(new Date()); 
		orderInfo.setDeliveryAddrId(0L); 
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName()); 
		
		orderInfo.setGoodsPrice(goods.getSeckillPrice()); 
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId()); 
		
		orderMapper.insertOrderInfo(orderInfo);
		
		SeckillOrder seckillOrder = new SeckillOrder();
		 seckillOrder.setGoodsId(goods.getId());
		 seckillOrder.setOrderId(orderInfo.getOrderId()); 
		 seckillOrder.setUserId(user.getId());
		
		orderMapper.insertSeckillOrder(seckillOrder);
		
		//生成订单后，需要写入缓存中
		redisService.set(SecKillActivityKey.getSecKillOrderByUidGid, ""+user.getId()+"_"+goods.getId(), seckillOrder);
		
		return orderInfo;
	}
	
	
	
	
 
	public OrderInfo getOrderById(long orderId) { 
		 return orderMapper.getOrderById(orderId);
	}

	
	public void deleteOrder(long goodsId, long userId) {
		this.orderMapper.deleteSeckillOrderByGoodIdAndUserId(goodsId,userId);
	}

	
	public List<SeckillOrder> getAllSeckillOrders() { 
	     return this.orderMapper.getAllSeckillOrders();
	}

	 
	
	
}
