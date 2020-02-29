package pers.yhf.seckill.config;
  
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author huangQiChang
 * 设置交换机 消息队列 以及相关的绑定信息
 */
@Configuration
public class RabbitMQComponentConfig {
	
	public static final String QUEUE = "queue";
	
	public static final String SECKILL_QUEUE = "seckillqueue"; 
	
	public static final boolean iSDURABLE = true;
	
	//设置消息是否持久化，1: 非持久化   2: 持久化
	public static final int DELIVERMODE = 2;
	
	public static final boolean EXCLUSIVE = false;
	
	public static final boolean AUTODELETE = true;
	
	public static final boolean AUTOACK = true;
	
	// 声明一个接收被删除的消息的交换机和队列
    public static final String EXCHANGE_DEAD_NAME = "exchange.dead";
    
    public static final String QUEUE_DEAD_NAME = "queue_dead";

    public static final String EXCHANGE_NAME = "exchange.fanout";
    
    public static final String ROUTEKEY_NAME = "routingkey.dead";
    
    

	/**
	 * 直接模式
	 * @return
	 */
	@Bean
	public Queue queue(){
		//队列持久化
	 return new Queue(SECKILL_QUEUE,iSDURABLE);
	}
	
	/**
	 * 主题模式
	 * @return
	 */
	/*@Bean
	public Queue topicQueue1(){
		return new Queue(TOPIC_QUEUE1,true);
	}
	
	@Bean
	public Queue topicQueue2(){
		return new Queue(TOPIC_QUEUE2,true);
	}*/
	
	/*@Bean
	public TopicExchange topicExchange(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}*/
	
	
	/**
	 * Fanout模式
	 * @return
	 */
	/*@Bean
	public FanoutExchange fanoutExchange(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}*/
	
	
	/**
	 * Header模式
	 * @return
	 */
	/*@Bean
	public HeadersExchange headersExchange(){
		return new HeadersExchange(HEADERS_EXCHANGE);
	}*/
	
	/*@Bean
	public Queue headersQueue1(){
		return new Queue(HEADERS_QUEUE,true);
	}*/
	
	 
	/*@Bean
	public Binding topicBinding1(){
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
	}
	@Bean
	public Binding topicBinding2(){
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
	}*/
	
	
	/**
	 * 广播模式绑定
	 * @return
	 */
	/*@Bean
	public Binding fanoutBinding1(){
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
	}
	@Bean
	public Binding fanoutBinding2(){
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
	}*/
	
	
	
	/**
	 * Headers模式绑定
	 * @return
	 *//*
	@Bean
	public Binding headersBinding(){
		Map<String,Object> map = new HashMap<String,Object>();
		  map.put("header1", "value1");
		  map.put("header2", "value2");
		return BindingBuilder.bind(headersQueue1()).to(headersExchange())
				.whereAll(map).match();
	}*/
	
	
	
	
	
	
	
	
	
	
	
	/*  @Bean
	    public TopicExchange topicExchange() {
	        return new TopicExchange("myEx");
	    }
	@Bean
	    public Binding binding() {
	        return BindingBuilder.bind(queue()).to(topicExchange()).with("myQ.*");
	    }

	    @Bean
	    public DirectExchange directExchange() {
	        return new DirectExchange("myDire",false,true);
	    }

	    @Bean
	    public Queue queue_not_in() {
	        return new Queue("myDire.notIn");
	    }

	    @Bean
	    public Binding directExchange_queue_not_in_binding() {
	        return BindingBuilder.bind(queue_not_in()).to(directExchange()).with("myDire.notIn");
	    }
*/
	
	
}
