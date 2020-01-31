package pers.yhf.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yhf.seckill.domain.User;
import pers.yhf.seckill.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	public User getUserById(int id){
		return userMapper.getUserById(id);
	}


	// @Transactional
	public boolean tx() { 
		User u1 = new User();
		u1.setId(2);
		u1.setName("222"); 
		userMapper.insert(u1);
		
		User u2 = new User();
		u2.setId(1);
		u2.setName("1111"); 
		userMapper.insert(u2);
		return true;
	}
	
}
