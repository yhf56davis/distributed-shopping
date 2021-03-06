package pers.yhf.seckill.redisCluster;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import pers.yhf.seckill.redisCluster.JedisClusterConfig;
import redis.clients.jedis.JedisCluster;

@Service
public class RedisService {

	private static final Logger log=LoggerFactory.getLogger(RedisService.class);
	
	@Autowired
    private JedisClusterConfig jedisClusterConfig;
	  
     

	public <T> boolean set(KeyPrefix prefix,String key, T value){
		JedisCluster jedisCluster = null;
         try{
        	jedisCluster = jedisClusterConfig.getJedisCluster();
        	
        	String str = beanToString(value);
		    if(str == null || str.length()<=0){
		    	return false;
		    }
		    //生成真正的key
		    String realKey = prefix.getPrefix()+key;
		    int seconds = prefix.expireSeconds();
		       //  System.out.println("seconds:"+seconds);
		    if(seconds<=0){
		    	jedisCluster.set(realKey, str);
		    }
		    else{
		    	jedisCluster.setex(realKey, seconds, str);
		    }
        	
            //String str=jedisCluster.set(key, String.valueOf(value));
        
            //jedis.set(realKey, str);
    		    return true;
    		 }
            catch (Exception ex){
            log.error("setToRedis:{Key:"+key+",value"+value+"}",ex);
            //returnToClusterPool(jedisCluster);
           }
         finally{
        	//returnToClusterPool(jedisCluster);
         }
         return false;
    }

    
     
    
	public <T> T get(KeyPrefix prefix,String key, Class<T> clazz){
		JedisCluster jedisCluster = null;
        try{ 
        	jedisCluster = jedisClusterConfig.getJedisCluster();
        	//生成真正的key
		    String realKey = prefix.getPrefix()+key;
            String str=jedisCluster.get(realKey);
            T t = stringToBean(str,clazz);
		    return t; 
        }
         finally{
        	//returnToClusterPool(jedisCluster);
         }
	 }
     
	 
	
	
	@SuppressWarnings("unchecked")
	public static<T> T stringToBean(String str, Class<T> clazz) {
		 if(str ==null || str.length()<=0 || clazz == null){
			 return null;
		 }
		  if(clazz == int.class || clazz == Integer.class){
				return (T)Integer.valueOf(str); 
			}
			else if(clazz == String.class){
				return (T)str;
			}
			else if(clazz == long.class || clazz == Long.class){
				return (T)Long.valueOf(str); 
			}
			else{ 
				return JSON.toJavaObject(JSON.parseObject(str), clazz);
			}
	    }

 
	
	 public static<T> String beanToString(T value) { 
		if(value == null){
			return null;
		}
		Class<?> clazz = value.getClass(); 
		if(clazz == int.class || clazz == Integer.class){
			return ""+value;
		}
		else if(clazz == String.class){
			return (String)value;
		}
		else if(clazz == long.class || clazz == Long.class){
			return ""+value;
		}
		else{
			return JSON.toJSONString(value);
		}
	 }
 

	
	/**
	 * 判断key是否存在
	 * */
	public <T> boolean exists(KeyPrefix prefix, String key) {
		    JedisCluster jedisCluster = null; 
			try{ 
				jedisCluster = jedisClusterConfig.getJedisCluster();
				//生成真正的key
				 String realKey  = prefix.getPrefix() + key;
				return jedisCluster.exists(realKey);
			}
			finally{
				//returnToClusterPool(jedisCluster);
			}
			
	     } 
	/**
	 * 增加值
	 * */
	public <T> Long incr(KeyPrefix prefix, String key) {
		JedisCluster jedisCluster = null; 
			 try{
				 jedisCluster = jedisClusterConfig.getJedisCluster();
					//生成真正的key
					 String realKey  = prefix.getPrefix() + key;
					return  jedisCluster.incr(realKey); 
			 }
			 finally{
				//returnToClusterPool(jedisCluster);
			 }
	  }
	
	
	/**
	 * 减少值
	 * */
	public <T> Long decr(KeyPrefix prefix, String key) {
		JedisCluster jedisCluster = null; 
		try{
			jedisCluster = jedisClusterConfig.getJedisCluster();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedisCluster.decr(realKey);
		}finally{
			//returnToClusterPool(jedisCluster);
		}
	 }
	
	
	/**
	 * 删除
	 * */
	public boolean delete(KeyPrefix prefix, String key) {
		JedisCluster jedisCluster = null; 
		try{
			 jedisCluster = jedisClusterConfig.getJedisCluster();
				//生成真正的key
				 String realKey  = prefix.getPrefix() + key;
				 long ret = jedisCluster.del(realKey);
				 return ret>0;
		}finally{
			//returnToClusterPool(jedisCluster);
		}
	 }
 
	
	
	//--------------------------------------------------------------------------//
	
	//我们使用的是redis3.0的集群，用jedis的JedisCluster.close()方法造成的集群连接关闭的情况。 
	//jedisCluster内部使用了池化技术，每次使用完毕都会自动释放Jedis因此不需要关闭。
	//否则会出现：  No reachable node in cluster
	private void returnToClusterPool(JedisCluster jedisCluster) { 
			if(jedisCluster!=null){
				try {
					jedisCluster.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
	}
	 
	 
	  
	
}
