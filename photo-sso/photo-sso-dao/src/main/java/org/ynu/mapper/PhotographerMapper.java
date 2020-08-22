package org.ynu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ynu.pojo.Photographer;

/**
 * @Description: 摄影师操作接口
 * @author: hys 
 * @date: 2020年3月29日
 */
public interface PhotographerMapper {
	
	/**
	 * @Description: 增加摄影师
	 * @param photographer
	 */
	public void addPhotographer(Photographer photographer);
	
	/**
	 * @Description: 删除摄影师
	 * @param pname
	 */
	public void deletePhotographerByName(String pname);
	
	/**
	 * @Description: 修改摄影师
	 * @param photographer
	 */
	public void updatePhotographer(Photographer photographer);
	
	
	
	/**
	 * @Description: 根据用户名查找摄影师
	 * @param pname
	 * @return
	 */
	public Photographer queryPhotographerByName(String pname);
	
	/**
	 * @Description: 根据用户名密码查找摄影师
	 * @param pname
	 * @param ppwd
	 * @return
	 */
	public Photographer queryPhotographerByNameAndPwd(@Param("pname")String pname, @Param("ppwd")String ppwd);
	
	/**
	 * @Description: 根据学校查找摄影师 价格升序
	 * @param school
	 * @return
	 */
	public List<Photographer> queryPhotographerBySchool(String school);
	
	/**
	 * @Description: 根据价格区间查找摄影师 价格升序
	 * @param low
	 * @param high
	 * @return
	 */
	public List<Photographer> queryPhotographerByPrice(@Param("low")int low, @Param("high")int high);
	
	
	
	/**
	 * @Description: 根据写真风格查找摄影师 价格升序
	 * @param style
	 * @return
	 */
	public List<Photographer> queryPhotographerByPortray(String style);
	
	/**
	 * @Description: 根据毕业照风格查找摄影师 价格升序
	 * @param style
	 * @return
	 */
	public List<Photographer> queryPhotographerByGraduation(String style);
	
	/**
	 * @Description: 根据私房风格查找摄影师 价格升序
	 * @param style
	 * @return
	 */
	public List<Photographer> queryPhotographerByRoom(String style);
	
	/**
	 * @Description: 根据情侣风格查找摄影师 价格升序
	 * @param style
	 * @return
	 */
	public List<Photographer> queryPhotographerByCouple(String style);
	
	/**
	 * @Description: 根据肖像风格查找摄影师 价格升序
	 * @param style
	 * @return
	 */
	public List<Photographer> queryPhotographerByFace(String style);
}
