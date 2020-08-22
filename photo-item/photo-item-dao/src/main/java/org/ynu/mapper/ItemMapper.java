package org.ynu.mapper;

import java.util.List;

import org.ynu.pojo.Item;
import org.ynu.pojo.Shop;

/**
 * @Description: 商品接口
 * @author: hys 
 * @date: 2020年6月3日
 */
public interface ItemMapper {
	
	/**
	 * @Description: 上传商品
	 * @param item
	 */
	public Long addItem(Item item);
	
	
	/**
	 * @Description: 查找所有商品
	 * @return
	 */
	public List<Item> queryAllItems();
	
	
	/**
	 * @Description: 根据名字查id
	 * @param name
	 * @return
	 */
	public Long queryIidByName(String name);
	
	
	
}
