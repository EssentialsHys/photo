package org.ynu.service;

import java.util.Map;

import org.ynu.pojo.Merchant;
import org.ynu.pojo.Photographer;
import org.ynu.pojo.User;

/**
 * @Description: 
 * @author: hys 
 * @date: 2020年3月31日
 */
public interface SSOService {
	
	/**
	 * @Description: 用户注册
	 * @param user
	 * @return
	 */
	public String register(User user);
	
	
	/**
	 * @Description: 用户登录
	 * @param user
	 * @return
	 */
	public Map<String,String> login(User user);
	
	
	/**
	 * @Description: 摄影师注册
	 * @param photographer
	 * @return
	 */
	public String register(Photographer photographer);
	
	
	/**
	 * @Description: 摄影师登录
	 * @param photographer
	 * @return
	 */
	public Map<String,String> login(Photographer photographer);
	
	
	/**
	 * @Description: 商家注册
	 * @param merchant
	 * @return
	 */
	public String register(Merchant merchant);
	
	
	/**
	 * @Description: 商家登录
	 * @param photographer
	 * @return
	 */
	public Map<String,String> login(Merchant merchant);
	
	
}
