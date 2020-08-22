package org.ynu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.ynu.mapper.ItemMapper;
import org.ynu.mapper.OrderMapper;
import org.ynu.mapper.PhotographerMapper;
import org.ynu.mapper.PictureMapper;
import org.ynu.mapper.ShoppingMapper;
import org.ynu.mapper.UserMapper;
import org.ynu.pojo.Order;
import org.ynu.pojo.OrderPic;
import org.ynu.pojo.Photographer;
import org.ynu.pojo.Shopping;
import org.ynu.pojo.User;
import org.ynu.service.OrderService;
import org.ynu.util.jedis.JedisClient;
import org.ynu.util.json.JsonUtils;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @Description: 订单服务实现类 
 * @author: hys 
 * @date: 2020年4月19日
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	final static String LONGITUDE_TO_ADDRESS_URL = "http://api.map.baidu.com/reverse_geocoding/v3/?output=json&coordtype=BD09&pois=1";
	final static String AK = "9MBLAxLPACfbpRB888wLymkDlsOjAPpz";
	   
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private PhotographerMapper photographerMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PictureMapper pictureMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ShoppingMapper shoppingMapper;
	
	@Override
	public String addOrder(Order order,String pname,String token, List<String> names) {
		// 取Session
		String uname;
		try {
			uname = jedisClient.get(token).replace("\"", "");
		} catch(NullPointerException e) {
			return "session expired";
		}
		
		// 设置订单起始时间
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime=dateFormat.format(date);
		order.setStartTime(startTime);
		
		// 添加
		Photographer p = photographerMapper.queryPhotographerByName(pname);
		order.setPhotographer(p);
		
		// 存在
		if (StringUtils.isNoneBlank(pname)) {
			User user = userMapper.queryUserByName(uname);
			order.setUser(user);
			// 生成订单
			orderMapper.addOrder(order);
			Long oid = order.getOid();
			Shopping s = new Shopping();
			s.setOid(oid);
			for(String name:names) {
				Long iid = itemMapper.queryIidByName(name);
				s.setIid(iid);
				// 生成购物单
				shoppingMapper.addShopping(s);
			}
			
			// 清理缓存
			String key = "queryOrder:"+pname;
			jedisClient.del(key);
			
			return "success";
		}
		return null;
	}

	@Override
	public String completeOrder(Long oid, String token, List<String> urls) {
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
		
		Photographer p = photographerMapper.queryPhotographerByName(pname);
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime=dateFormat.format(date);	
		
		Order order = orderMapper.queryOrderByOid(oid);
		if(order.getNum() != urls.size()) {
			return "wrong nums";
		}
		order.setEndTime(endTime);
		orderMapper.endOrder(order);
		
		OrderPic opic = new OrderPic();
		opic.setOrder(order);
		
		for(String url:urls) {
			opic.setUrl(url);
			pictureMapper.uploadOrder(opic);
		}
		// 清理缓存
		String key = "queryOrder:"+pname;
		jedisClient.del(key);
		
		return "success";
	}

	@Override
	public List<Order> queryOrder(String pname) {
		String key = "queryOrder:"+pname;
		String info = jedisClient.get(key);	
		// 有数据查缓存
		if(StringUtils.isNoneBlank(info)) {
			jedisClient.expire(key, 600);
			return JsonUtils.jsonToList(info, Order.class); 
		}	
		// 没数据进数据库并写入缓存
		Photographer p = photographerMapper.queryPhotographerByName(pname);
		List<Order> list = orderMapper.queryOrderByPid(p.getPid());
		//jedisClient.rpush(key, JsonUtils.objectToJson(list));
		jedisClient.set(key, JsonUtils.objectToJson(list));
		jedisClient.expire(key, 600);
		return list;
	}

	@Override
	public String startOrder(double lat, double lng, Long oid, String token) {
		// 获取地址
		String url = LONGITUDE_TO_ADDRESS_URL + "&ak=" + AK + "&location=" + lat + "," + lng;
		HttpClient client = HttpClients.createDefault(); // 创建默认http连接
		HttpGet get = new HttpGet(url); // 创建一个get请求
		
		try {
			HttpResponse response = client.execute(get); // 用http连接去执行get请求并且获得http响应
			HttpEntity entity = response.getEntity(); // 从response中取到响实体
			String html = EntityUtils.toString(entity); // 把响应实体转成文本
			
			JSONObject jsonObject = new JSONObject(html);
			String address = jsonObject.getJSONObject("result").getString("formatted_address");
			Order order = orderMapper.queryOrderByOid(oid);
			
			if(address.equals(order.getLocation())) {
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startTime = dateFormat.format(date);	
				order.setStartTime(startTime);
				orderMapper.startOrder(order);
				return "success";
			}
			return "fail";
		} catch (Exception e) {
			return "fail";
		}
		
	}

	@Override
	public Photographer queryPhotographerByName(String pname) {			
		String key = "queryPhotographerByName:"+pname;
		String info = jedisClient.get(key);
		
		// 有数据查缓存
		if(StringUtils.isNoneBlank(info)) {
			jedisClient.expire(key, 600+(int)(Math.random()*100));
			return JsonUtils.jsonToPojo(info, Photographer.class); 
		}
		
		// 没数据进数据库并写入缓存
		Photographer p = photographerMapper.queryPhotographerByName(pname);
		if(p.getPortray()!=null) {
			p.getStyle().add(p.getPortray());
		}
		if(p.getGraduation()!=null) {
			p.getStyle().add(p.getGraduation());
		}
		if(p.getRoom()!=null) {
			p.getStyle().add(p.getRoom());
		}
		if(p.getCouple()!=null) {
			p.getStyle().add(p.getCouple());
		}
		if(p.getFace()!=null) {
			p.getStyle().add(p.getFace());
		}
		
		jedisClient.set(key, JsonUtils.objectToJson(p));
		jedisClient.expire(key, 1200+(int)(Math.random()*100));
		return p;
	}

	@Override
	public List<Photographer> queryPhotographerBySchool(String school) {	
		String key = "queryPhotographerBySchool:"+school;
		String info = jedisClient.get(key);
		
		// 有数据查缓存
		if(StringUtils.isNoneBlank(info)) {
			jedisClient.expire(key, 600+(int)(Math.random()*100));
			return JsonUtils.jsonToList(info, Photographer.class); 
		}
		
		// 没数据进数据库并写入缓存
		List<Photographer> list = photographerMapper.queryPhotographerBySchool(school);
		if(list!=null) {
			Iterator<Photographer> it = list.iterator();
			while(it.hasNext()) {
				Photographer p = it.next();
				if(p.getPortray()!=null) {
					p.getStyle().add(p.getPortray());
				}
				if(p.getGraduation()!=null) {
					p.getStyle().add(p.getGraduation());
				}
				if(p.getRoom()!=null) {
					p.getStyle().add(p.getRoom());
				}
				if(p.getCouple()!=null) {
					p.getStyle().add(p.getCouple());
				}
				if(p.getFace()!=null) {
					p.getStyle().add(p.getFace());
				}		
			}
		}
		//jedisClient.rpush(key, JsonUtils.objectToJson(list));
		jedisClient.set(key, JsonUtils.objectToJson(list));
		jedisClient.expire(key, 1200+(int)(Math.random()*100));
		return list;
	}

	@Override
	public List<Photographer> queryPhotographerByPrice(int low, int high) {
		String key = "queryPhotographerByPrice:"+low+"-"+high;
		String info = jedisClient.get(key);
		
		// 有数据查缓存
		if(StringUtils.isNoneBlank(info)) {
			return JsonUtils.jsonToList(info, Photographer.class); 
		}
		
		// 没数据进数据库并写入缓存
		List<Photographer> list = photographerMapper.queryPhotographerByPrice(low, high);
		if(list!=null) {
			Iterator<Photographer> it = list.iterator();
			while(it.hasNext()) {
				Photographer p = it.next();
				if(p.getPortray()!=null) {
					p.getStyle().add(p.getPortray());
				}
				if(p.getGraduation()!=null) {
					p.getStyle().add(p.getGraduation());
				}
				if(p.getRoom()!=null) {
					p.getStyle().add(p.getRoom());
				}
				if(p.getCouple()!=null) {
					p.getStyle().add(p.getCouple());
				}
				if(p.getFace()!=null) {
					p.getStyle().add(p.getFace());
				}		
			}
		}	
		//jedisClient.rpush(key, JsonUtils.objectToJson(list));
		jedisClient.set(key, JsonUtils.objectToJson(list));
		jedisClient.expire(key, 30+(int)(Math.random()*20));
		return list;
	}

	@Override
	public List<Photographer> queryPhotographerByStyle(String style) {
		String key = "queryPhotographerByStyle:"+style;
		String info = jedisClient.get(key);
		
		// 有数据查缓存
		if(StringUtils.isNoneBlank(info)) {
			jedisClient.expire(key, 100+(int)(Math.random()*100));
			return JsonUtils.jsonToList(info, Photographer.class); 
		}
		
		// 没数据进数据库并写入缓存
		List<Photographer> list = null;			
		switch (style) {
			case "写真":
				list = photographerMapper.queryPhotographerByPortray(style);
				if(list!=null) {
					Iterator<Photographer> it = list.iterator();
					while(it.hasNext()) {
						Photographer p = it.next();
						if(p.getPortray()!=null) {
							p.getStyle().add(p.getPortray());
						}
						if(p.getGraduation()!=null) {
							p.getStyle().add(p.getGraduation());
						}
						if(p.getRoom()!=null) {
							p.getStyle().add(p.getRoom());
						}
						if(p.getCouple()!=null) {
							p.getStyle().add(p.getCouple());
						}
						if(p.getFace()!=null) {
							p.getStyle().add(p.getFace());
						}		
					}
				}
				break;
			case "毕业照":
				list = photographerMapper.queryPhotographerByGraduation(style);
				if(list!=null) {
					Iterator<Photographer> it = list.iterator();
					while(it.hasNext()) {
						Photographer p = it.next();
						if(p.getPortray()!=null) {
							p.getStyle().add(p.getPortray());
						}
						if(p.getGraduation()!=null) {
							p.getStyle().add(p.getGraduation());
						}
						if(p.getRoom()!=null) {
							p.getStyle().add(p.getRoom());
						}
						if(p.getCouple()!=null) {
							p.getStyle().add(p.getCouple());
						}
						if(p.getFace()!=null) {
							p.getStyle().add(p.getFace());
						}		
					}
				}
				break;
			case "私房":
				list = photographerMapper.queryPhotographerByRoom(style);
				if(list!=null) {
					Iterator<Photographer> it = list.iterator();
					while(it.hasNext()) {
						Photographer p = it.next();
						if(p.getPortray()!=null) {
							p.getStyle().add(p.getPortray());
						}
						if(p.getGraduation()!=null) {
							p.getStyle().add(p.getGraduation());
						}
						if(p.getRoom()!=null) {
							p.getStyle().add(p.getRoom());
						}
						if(p.getCouple()!=null) {
							p.getStyle().add(p.getCouple());
						}
						if(p.getFace()!=null) {
							p.getStyle().add(p.getFace());
						}		
					}
				}
				break;
			case "情侣":
				list = photographerMapper.queryPhotographerByCouple(style);
				if(list!=null) {
					Iterator<Photographer> it = list.iterator();
					while(it.hasNext()) {
						Photographer p = it.next();
						if(p.getPortray()!=null) {
							p.getStyle().add(p.getPortray());
						}
						if(p.getGraduation()!=null) {
							p.getStyle().add(p.getGraduation());
						}
						if(p.getRoom()!=null) {
							p.getStyle().add(p.getRoom());
						}
						if(p.getCouple()!=null) {
							p.getStyle().add(p.getCouple());
						}
						if(p.getFace()!=null) {
							p.getStyle().add(p.getFace());
						}		
					}
				}
				break;
			case "肖像":
				list = photographerMapper.queryPhotographerByFace(style);
				if(list!=null) {
					Iterator<Photographer> it = list.iterator();
					while(it.hasNext()) {
						Photographer p = it.next();
						if(p.getPortray()!=null) {
							p.getStyle().add(p.getPortray());
						}
						if(p.getGraduation()!=null) {
							p.getStyle().add(p.getGraduation());
						}
						if(p.getRoom()!=null) {
							p.getStyle().add(p.getRoom());
						}
						if(p.getCouple()!=null) {
							p.getStyle().add(p.getCouple());
						}
						if(p.getFace()!=null) {
							p.getStyle().add(p.getFace());
						}		
					}
				}
				break;
		} //switch
		//jedisClient.rpush(key, JsonUtils.objectToJson(list));
		jedisClient.set(key, JsonUtils.objectToJson(list));
		jedisClient.expire(key, 120+(int)(Math.random()*100));
		return list;		
	}
	

	

}
