package org.ynu.mapper;

import org.apache.ibatis.annotations.Param;
import org.ynu.pojo.Merchant;

/**
 * @Description: 商家操作接口
 * @author: hys 
 * @date: 2020年6月2日
 */
public interface MerchantMapper {
	
	/**
	 * @Description: 增加商家
	 * @param merchant
	 */
	public void addMerchant(Merchant merchant);
	
	/**
	 * @Description: 根据用户名密码查找商家 
	 * @param mname
	 * @param mpwd
	 */
	public Merchant queryMerchantByMnameAndMpwd(@Param("mname")String mname, @Param("mpwd")String mpwd);
	
	/**
	 * @Description: 根据地址查商家
	 * @param maddress
	 */
	public Merchant queryMerchantByMaddress(String maddress);
	
	
	/**
	 * @Description: 根据商家名查id
	 * @param mname
	 * @return
	 */
	public Long queryMidByMname(String mname);
}
