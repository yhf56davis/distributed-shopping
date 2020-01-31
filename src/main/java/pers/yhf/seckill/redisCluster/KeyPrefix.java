package pers.yhf.seckill.redisCluster;

public interface KeyPrefix {

	public int expireSeconds();
	
	public int expireSeconds(int seconds);
	
	public String getPrefix();
	
}
