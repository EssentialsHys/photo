package org.ynu.service;

/**
 * @Description: 会员服务
 * @author: hys 
 * @date: 2020年6月6日
 */
public interface VIPService {
	
	/**
	 * @Description: socket编程-智能换衣
	 * @param fileName1
	 * @param fileName2
	 */
	public String changeClothes(String fileName1, String fileName2);
	
	/**
	 * @Description: socket编程-智能换背景
	 * @param bytes1
	 * @param bytes2
	 * @param ext1
	 * @param ext2
	 * @return
	 */
	public String changeBackground(byte[] bytes1,byte[] bytes2,String ext1, String ext2);
}
