package org.ynu.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.ynu.pojo.Order;

public class TestAPI {
	
	final static String LONGITUDE_TO_ADDRESS_URL = "http://api.map.baidu.com/reverse_geocoding/v3/?output=json&coordtype=BD09&pois=1";
	final static String AK = "9MBLAxLPACfbpRB888wLymkDlsOjAPpz";
	
	@Test
	public void test() {
		// 获取地址
		String url = LONGITUDE_TO_ADDRESS_URL + "&ak=" + AK + "&location=24.88,102.80";
		HttpClient client = HttpClients.createDefault(); // 创建默认http连接
		HttpGet get = new HttpGet(url); // 创建一个get请求
		
		try {
			HttpResponse response = client.execute(get); // 用http连接去执行get请求并且获得http响应
			HttpEntity entity = response.getEntity(); // 从response中取到响实体
			String html = EntityUtils.toString(entity); // 把响应实体转成文本
			
			JSONObject jsonObject=new JSONObject(html);
			String a = jsonObject.toString();
			System.out.println(a);
			String address = jsonObject.getJSONObject("result").getString("formatted_address");
			System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
