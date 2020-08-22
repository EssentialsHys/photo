package org.ynu.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.ynu.mapper.PhotographerMapper;
import org.ynu.mapper.PictureMapper;
import org.ynu.pojo.Photographer;
import org.ynu.pojo.Picture;
import org.ynu.service.PictureService;
import org.ynu.util.fdfs.FastDFSClient;
import org.ynu.util.jedis.JedisClient;
import org.ynu.util.json.JsonUtils;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @Description: 图片操作实现类
 * @author: hys 
 * @date: 2020年4月9日
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService{
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private PictureMapper pictureMapper;
	
	@Autowired
	private PhotographerMapper photographerMapper;
	
	@Value("${FASTDFS_SERVER}")
	private String FASTDFS_SERVER;
	
	@Override
	public String upload(byte[] bytes, String ext,String token) {
		if(token.indexOf("PHOTOGRAPHER")==-1) {
			return "identity fail";
		}
		// 取Session
		String pname;
		try {
			pname = jedisClient.get(token).replace("\"", "");
		} catch(NullPointerException e) {
			return "session expired";
		}
		// 存在
		if (StringUtils.isNoneBlank(pname)) {
			
			FastDFSClient client = new FastDFSClient("classpath:client.conf");
			String url = null;
			try {
				url = FASTDFS_SERVER + client.uploadFile(bytes, ext);
			} catch (IOException e) {
				return "fail";
			} catch (Exception e) {
				return "fail";
			}
			Photographer photographer = photographerMapper.queryPhotographerByName(pname);
			Picture picture = new Picture();
			picture.setPhotographer(photographer);
			picture.setUrl(url);
			pictureMapper.upload(picture);		
			// 清空缓存
			String key = "picsInf:"+pname;
			jedisClient.del(key);
			return "success";
		}
		return null;
	}

	@Override
	public List<String> queryUserPics(String pname) {
		String key = "picsInf:"+pname;
		// 读缓存
		List<String> info = jedisClient.lrange(key, 0, 0);
		// 有数据查缓存
		if (info.size()>0) {
			jedisClient.expire(key, 600+(int)(Math.random()*100));
			return info; 
		}
		
		// 没数据就查数据库并写入缓存
		Photographer photographer = photographerMapper.queryPhotographerByName(pname);
		List<String> urls = pictureMapper.queryUserPics(photographer);
		
		jedisClient.rpush(key, JsonUtils.objectToJson(urls));
		jedisClient.expire(key, 600+(int)(Math.random()*100));
		return urls;
	}


}
