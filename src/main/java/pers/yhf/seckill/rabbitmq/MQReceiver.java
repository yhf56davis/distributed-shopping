package pers.yhf.seckill.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import pers.yhf.seckill.config.RabbitMQComponentConfig;
import pers.yhf.seckill.domain.SeckillOrder;
import pers.yhf.seckill.domain.SeckillUser;
import pers.yhf.seckill.redisCluster.RedisService;
import pers.yhf.seckill.service.GoodsService;
import pers.yhf.seckill.service.SeckillService;
import pers.yhf.seckill.util.ReadUtil;
import pers.yhf.seckill.service.OrderService;
import pers.yhf.seckill.vo.GoodsVo;
 

@Service
public class MQReceiver {

	private Logger log = LoggerFactory.getLogger(MQReceiver.class);
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrderService orderService; 
	
	@Autowired
	private SeckillService seckillService;
	
	/**
	 * Direct模式
	 * @param message
	 * @throws IOException 
	 */
	@RabbitListener(queues=RabbitMQComponentConfig.SECKILL_QUEUE)
	public void receive(String message) throws IOException{ 
        
		System.out.println("receive message: "+message);
		
		
	  SeckillMessage mm = RedisService.stringToBean(message, SeckillMessage.class);
		SeckillUser user = mm.getUser();
	     long goodsId = mm.getGoodsId();
		
		/*String userIdStr = message.split("-")[0];
	    String goodIdStr = message.split("-")[1];
	    Long userId = Long.valueOf(userIdStr);
	    long goodsId = Long.valueOf(goodIdStr);*/
	     
	     
	     GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
	     int stock = goods.getStockCount();
	     if(stock<=0) return; 
	     
	   //判断是否秒杀到了
	        //这里可做的优化：可以不用查数据库，当生成订单时 ，我们把订单写入缓存里了，故只需要查缓存即可
	     
	     //SeckillOrder order = orderService.getSeckillOrderByUserIdAndGoodsId(user.getId(),goodsId);
	     
	     SeckillOrder order = orderService.getSeckillOrderByUserIdAndGoodsId(user.getId(),goodsId);
	     
	     
	     if(order != null) return ; 
	     
	   //减库存    下订单  写入秒杀订单
	     this.seckillService.seckill(user,goods);
		
     //-----------------------------------------------------------------------------------//
		
		/*Connection connection = null;
        Channel channel = null;
        String rabbitmqHost = ReadUtil.getContentFromProperties("rabbitMQHost");
	    String rabbitMQUserName = ReadUtil.getContentFromProperties("rabbitMQUserName");
	    String rabbitMQPassword = ReadUtil.getContentFromProperties("rabbitMQPassword");
	    
        try {

            ConnectionFactory factory = new ConnectionFactory();
              factory.setHost(rabbitmqHost);
              factory.setPort(5672);
              factory.setUsername(rabbitMQUserName);
              factory.setPassword(rabbitMQPassword);
              factory.setVirtualHost("/");
            
            connection = factory.newConnection();
            channel = connection.createChannel();

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                        byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(envelope.getExchange() + "," + envelope.getRoutingKey() + "," + message);
                }
            };
            // channel绑定队列，autoAck为true表示一旦收到消息则自动回复确认消息
            channel.basicConsume(MQConfig.SECKILL_QUEUE, MQConfig.AUTOACK, consumer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		 
	        */
		
		
	//----------------------------------------------------------------------------//	
		
		
	   
	     
	}
	
	/*
	@RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
	public void receiveTopic1(String message){
		log.info("topic queue1 message: "+message);
	}
	
	
	@RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
	public void receiveTopic2(String message){
		log.info("topic queue2 message: "+message);
	}
	
	
	@RabbitListener(queues=MQConfig.HEADERS_QUEUE)
	public void receiveHeaderQueue(byte[] message){
		log.info("header queue message: "+new String(message));
	}*/
	
	
}
