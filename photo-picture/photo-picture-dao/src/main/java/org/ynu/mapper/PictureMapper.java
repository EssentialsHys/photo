package org.ynu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ynu.pojo.OrderPic;
import org.ynu.pojo.Photographer;
import org.ynu.pojo.Picture;

/**
 * @Description: 图片接口 
 * @author: hys 
 * @date: 2020年4月9日
 */
public interface PictureMapper {
	
	/**
	 * @Description: 上传图片
	 * @param picture
	 */
	public void upload(Picture picture);
	
	/**
	 * @Description: 上传订单图片
	 * @param opic
	 */
	public void uploadOrder(OrderPic opic);
	
	/**
	 * @Description: 上传商品图片
	 * @param picture
	 */
	public void uploadItem(String url);
	
	/**
	 * @Description: 查找用户的图片
	 * @param photographer
	 * @return
	 */
	public List<String> queryUserPics(Photographer photographer);
}
