package org.ynu.service;

import java.util.List;

import org.ynu.pojo.Order;
import org.ynu.pojo.Photographer;

/**
 * @Description: 订单服务接口 
 * @author: hys 
 * @date: 2020年4月18日
 */
public interface OrderService {
	
	/**
	 * @Description: 根据名字查找摄影师 要处理摄影风格
	 * @param pname
	 * @return
	 */
	public Photographer queryPhotographerByName(String pname);
	
	
	/**
	 * @Description: 根据学校查找摄影师 要处理摄影风格
	 * @param school
	 * @return
	 */
	public List<Photographer> queryPhotographerBySchool(String school);

	
	/**
	 * @Description: 根据价格区间查找摄影师 要处理摄影风格
	 * @param low
	 * @param high
	 * @return
	 */
	public List<Photographer> queryPhotographerByPrice(int low, int high);

	
	/**
	 * @Description: 根据风格查找摄影师
	 * @param style
	 * @return
	 */
	public List<Photographer> queryPhotographerByStyle(String style);
	
	
	/**
	 * @Description: 添加订单
	 * @param order
	 * @param pname
	 * @param token
	 * @return
	 */
	public String addOrder(Order order,String pname,String token, List<String> names);
	
	
	/**
	 * @Description: 完成订单
	 * @param oid
	 * @param token
	 * @param urls
	 * @return
	 */
	public String completeOrder(Long oid,String token, List<String> urls);
	
	/**
	 * @Description: 查找订单 时间降序
	 * @param pname
	 * @return
	 */
	public List<Order> queryOrder(String pname);
	
	/**
	 * @Description: 开始订单 通过经纬度，获取到详细地理位置信息
	 * @param lat
	 * @param lng
	 */
	public String startOrder(double lat, double lng, Long oid, String token);
}
