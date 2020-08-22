package org.ynu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ynu.mapper.ItemMapper;
import org.ynu.mapper.MerchantMapper;
import org.ynu.mapper.ShopMapper;
import org.ynu.pojo.Item;
import org.ynu.pojo.Photographer;
import org.ynu.pojo.Shop;
import org.ynu.service.ItemService;
import org.ynu.util.fdfs.FastDFSClient;
import org.ynu.util.jedis.JedisClient;
import org.ynu.util.json.JsonUtils;

/**
 * @Description: 商品实现类
 * @author: hys 
 * @date: 2020年6月3日
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${FASTDFS_SERVER}")
	private String FASTDFS_SERVER;
	
	@Override
	public String addItem(Item item, String token, byte[] bytes, String ext) {
		if(token.indexOf("MERCHANT")==-1) {
			return "identity fail";
		}
		// 取Session
		String mname;
		try {
			mname = jedisClient.get(token).replace("\"", "");
		} catch(NullPointerException e) {
			return "session expired";
		}
		
		FastDFSClient client = new FastDFSClient("classpath:client.conf");
		String url = null;
		try {
			url = FASTDFS_SERVER + client.uploadFile(bytes, ext);
		} catch (IOException e) {
			return "fail";
		} catch (Exception e) {
			return "fail";
		}
		item.setPic(url);
		// 上传商品
		itemMapper.addItem(item);
		Long iid = item.getIid();
		Long mid = merchantMapper.queryMidByMname(mname);
		Shop shop = new Shop();
		shop.setIid(iid);
		shop.setMid(mid);
		// 更新店铺
		shopMapper.updateShop(shop);
		return "success";
	}

	@Override
	public List<Item> queryAllItem() {
		String key = "items";
		// 读缓存
		String info = jedisClient.get(key);
		// 有数据查缓存
		if (StringUtils.isNoneBlank(info)) {	
			jedisClient.expire(key, 600+(int)(Math.random()*100));
			return JsonUtils.jsonToList(info, Item.class); 
		}
		// 没数据就查数据库并写入缓存
		List<Item> items = itemMapper.queryAllItems();
		//jedisClient.rpush(key, JsonUtils.objectToJson(items));
		jedisClient.set(key, JsonUtils.objectToJson(items));
		jedisClient.expire(key, 600+(int)(Math.random()*100));
		return items;
	}

	@Override
	public void deliverItem(Item item, String token) {
		// TODO Auto-generated method stub
		
	}

	

}
