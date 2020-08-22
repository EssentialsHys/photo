package org.ynu.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.ynu.mapper.MerchantMapper;
import org.ynu.mapper.PhotographerMapper;
import org.ynu.mapper.UserMapper;
import org.ynu.pojo.Merchant;
import org.ynu.pojo.Photographer;
import org.ynu.pojo.User;
import org.ynu.service.SSOService;
import org.ynu.util.jedis.JedisClient;
import org.ynu.util.json.JsonUtils;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @Description: 单点登录实现类
 * @author: hys 
 * @date: 2020年4月4日
 */
@Service
@Transactional
public class SSOServiceImpl implements SSOService{

	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PhotographerMapper photographerMapper;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Override
	public String register(User user) {
		if(userMapper.queryUserByName(user.getUname()) == null) {
			userMapper.addUser(user);
			return "success";
		}
		return "fail";
	}

	@Override
	public Map<String,String> login(User user) {
		Map<String,String> map = new HashMap<String,String>();
		if(userMapper.queryUserByNameAndPwd(user.getUname(), user.getUpwd()) != null) {	
			// 登录信息写入session
			// 使用redis模拟session
			// 生成token 使用UUID生成
			String token = UUID.randomUUID().toString().replace("-", "");
			token = "SESSION-USER:"+token;
			jedisClient.set(token, JsonUtils.objectToJson(user.getUname()));
			jedisClient.expire(token, 60*30);
			
			map.put("token", token);
			map.put("result", "success");
			return map;
		}
		map.put("token", null);
		map.put("result", "fail");
		return map;
	}

	@Override
	public String register(Photographer photographer) {
		if(photographerMapper.queryPhotographerByName(photographer.getPname()) == null) {
			photographerMapper.addPhotographer(photographer);
			// 清空缓存
			String key = "queryPhotographerBySchool:"+photographer.getSchool();
			jedisClient.del(key);
			
			if(photographer.getPortray()!=null) {
				String key1 = "queryPhotographerByStyle:"+photographer.getPortray();
				jedisClient.del(key1);
			}
			if(photographer.getGraduation()!=null) {
				String key2 = "queryPhotographerByStyle:"+photographer.getGraduation();
				jedisClient.del(key2);
			}
			if(photographer.getRoom()!=null) {
				String key3 = "queryPhotographerByStyle:"+photographer.getRoom();
				jedisClient.del(key3);
			}
			if(photographer.getCouple()!=null) {
				String key4 = "queryPhotographerByStyle:"+photographer.getCouple();
				jedisClient.del(key4);
			}
			if(photographer.getFace()!=null) {
				String key5 = "queryPhotographerByStyle:"+photographer.getFace();
				jedisClient.del(key5);
			}
				
			return "success";
		}
		return "fail";
	}

	@Override
	public Map<String,String> login(Photographer photographer) {
		Map<String,String> map = new HashMap<String,String>();
		if(photographerMapper.queryPhotographerByNameAndPwd(photographer.getPname(), photographer.getPpwd()) != null) {
			// 登录信息写入session
			// 使用redis模拟session
			// 生成token 使用UUID生成
			String token = UUID.randomUUID().toString().replace("-", "");
			token = "SESSION-PHOTOGRAPHER:" + token;
			jedisClient.set(token, JsonUtils.objectToJson(photographer.getPname()));
			jedisClient.expire(token, 60*30);
			
			map.put("token", token);
			map.put("result", "success");
			return map;
		}
		map.put("token", null);
		map.put("result", "fail");
		return map;
	}

	@Override
	public String register(Merchant merchant) {
		if(merchantMapper.queryMerchantByMnameAndMpwd(merchant.getMname(), merchant.getMpwd()) == null) {
			merchantMapper.addMerchant(merchant);
			return "success";
		}
		return "fail";
	}

	@Override
	public Map<String, String> login(Merchant merchant) {
		Map<String,String> map = new HashMap<String,String>();
		if(merchantMapper.queryMerchantByMnameAndMpwd(merchant.getMname(), merchant.getMpwd()) != null) {	
			// 登录信息写入session
			// 使用redis模拟session
			// 生成token 使用UUID生成
			String token = UUID.randomUUID().toString().replace("-", "");
			token = "SESSION-MERCHANT:"+token;
			jedisClient.set(token, JsonUtils.objectToJson(merchant.getMname()));
			jedisClient.expire(token, 60*30);
			
			map.put("token", token);
			map.put("result", "success");
			return map;
		}
		map.put("token", null);
		map.put("result", "fail");
		return map;
	}

}
