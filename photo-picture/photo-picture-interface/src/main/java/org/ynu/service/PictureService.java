package org.ynu.service;

import java.util.List;


/**
 * @Description: 图片操作接口
 * @author: hys 
 * @date: 2020年4月9日
 */
public interface PictureService {
	
	/**
	 * @Description: 上传图片
	 * @param urls
	 * @param token
	 * @return
	 */
	public String upload(byte[] bytes, String ext,String token);
	
	
	
	/**
	 * @Description: 查找用户的图片
	 * @param pname
	 * @return
	 */
	public List<String> queryUserPics(String pname);
}
