package org.ynu.service;

import java.util.List;

import org.ynu.pojo.Item;

/**
 * @Description: 商品服务
 * @author: hys 
 * @date: 2020年6月3日
 */
public interface ItemService {
	
	/**
	 * @Description: 添加商品
	 * @param item
	 * @param token
	 * @return
	 */
	public String addItem(Item item, String token, byte[] bytes, String ext);
	
	
	/**
	 * @Description: 查询所有商品
	 * @return
	 */
	public List<Item> queryAllItem();
	
	
	/**
	 * @Description: 发送商品 
	 * @param item
	 * @param token
	 */
	public void deliverItem(Item item, String token);
	
}
