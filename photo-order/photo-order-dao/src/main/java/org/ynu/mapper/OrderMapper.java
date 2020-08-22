package org.ynu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ynu.pojo.Order;

/**
 * @Description: 订单Mapper接口 
 * @author: hys 
 * @date: 2020年4月18日
 */
public interface OrderMapper {
	
	/**
	 * @Description: 添加订单
	 * @param order
	 */
	public Long addOrder(Order order);
	
	
	/**
	 * @Description: 订单开始
	 * @param order
	 */
	public void startOrder(Order order);
	
	/**
	 * @Description: 结束订单
	 * @param order
	 */
	public void endOrder(Order order);
	
	/**
	 * @Description: 查找摄影师的订单 时间降序
	 * @param pname
	 * @return
	 */
	public List<Order> queryOrderByPname(String pname);
	
	/**
	 * @Description: 查找用户的订单 时间降序
	 * @param uname
	 * @return
	 */
	public List<Order> queryOrderByUname(String uname);
	
	/**
	 * @Description: 根据订单id查找订单
	 * @param oid
	 * @return
	 */
	public Order queryOrderByOid(Long oid);
	
	/**
	 * @Description: 根据oid查pid
	 * @param oid
	 * @return
	 */
	public Long queryPidByOid(Long oid);
	
	/**
	 * @Description: 根据pid查订单
	 * @param pid
	 * @return
	 */
	public List<Order> queryOrderByPid(Long pid);
	
	
	
}
