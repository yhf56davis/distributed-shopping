package pers.yhf.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pers.yhf.seckill.domain.User; 
 
 
@Mapper
public interface UserMapper {
	 
	public User getUserById(@Param("id")int id);
	 
	public int insert(User user);

	public void decrease(User user); 

}
