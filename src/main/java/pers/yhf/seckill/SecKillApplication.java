package pers.yhf.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
  

@SpringBootApplication
@MapperScan("pers.yhf.seckill.mapper") 
public class SecKillApplication extends WebMvcConfigurerAdapter implements CommandLineRunner{
	 
    public static void main(String[] args) throws Exception {
    	
    	SpringApplication.run(SecKillApplication.class, args);
        
    }

	@Override
	public void run(String... arg0) throws Exception { 
		System.out.println("系统启动完毕...");
	}
    
  
    
}
