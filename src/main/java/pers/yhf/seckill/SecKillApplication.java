package pers.yhf.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
 

@SpringBootApplication
@MapperScan("pers.yhf.seckill.mapper") 
public class SecKillApplication 
    extends WebMvcConfigurerAdapter implements CommandLineRunner{
	 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SecKillApplication.class, args);
        //System.out.println("系统启动完毕...2");
        
        /*SeckillOrderConfig config = new SeckillOrderConfig();
 	    ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(5);
          exec.scheduleAtFixedRate(new Runnable() {
         	 //每隔一段时间打印系统时间，证明两者是互不影响的
            public void run() {
         	   config.cancelOrder();
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);*/
        
       /* SeckillOrderConfig config = new SeckillOrderConfig();
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
        
        exec.scheduleAtFixedRate(new Runnable() {//每隔一段时间打印系统时间，证明两者是互不影响的
            public void run() {
                //System.out.println("每隔一段时间打印系统时间，证明两者是互不影响的" + System.nanoTime());
            	config.cancelOrder();
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
*/
    }
    
    
    @Override
	public void run(String... arg0) throws Exception {
		System.out.println("系统启动完毕...");  
	 }
    
    
   
    
    
    
}
