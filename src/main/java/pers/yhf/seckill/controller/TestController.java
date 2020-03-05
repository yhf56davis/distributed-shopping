package pers.yhf.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.yhf.seckill.result.Result; 
import pers.yhf.seckill.service.UserService; 

@Controller 
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/db/tx2")
 	@ResponseBody
 	public  Result<Boolean> dbTx(){
 		   this.userService.txDecrease();
 	    return Result.success(true);
 	}
	
	
}
