package pers.yhf.seckill.util;

import java.util.Random;

public class TimeUtil {
	
	public static int initalTime = 4;
	
	public static int maxtTime = 10;

	public static void main(String[] args) {

		System.out.println(getRandomTimeValue());
		
	} 
	
	/**
	 * 获取随机数值  (单位：秒)
	 * @param maxtTime
	 * @return
	 */
	public static int getRandomTimeValue(){
		Random rand = new Random();
		return 60*(initalTime + rand.nextInt(maxtTime));
	}
	

}
