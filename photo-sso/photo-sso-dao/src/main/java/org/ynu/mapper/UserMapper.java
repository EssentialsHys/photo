package org.ynu.mapper;

import org.apache.ibatis.annotations.Param;
import org.ynu.pojo.User;

/**
 * @Description: 用户操作接口
 * @author: hys 
 * @date: 2020年3月29日
 */
public interface UserMapper {

	/*
	 * @Description 增加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/*
	 * @Description 删除用户
	 * @param uname
	 */
	public void deleteUserByName(String uname);
	
	/*
	 * @Description 修改用户
	 * @param user
	 */
	public void updateUser(User user);
	
	/*
	 * @Description 根据用户名查找用户
	 * @param uname
	 * @return User
	 */
	public User queryUserByName(String uname);
	
	/*
	 * @Description 用户登录
	 * @param uname
	 * @param upwd
	 * @return User
	 */
	public User queryUserByNameAndPwd(@Param("uname")String uname, @Param("upwd")String upwd);
}
