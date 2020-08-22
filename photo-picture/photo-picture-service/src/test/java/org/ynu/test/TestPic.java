package org.ynu.test;

import static org.junit.Assert.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestPic {

	//@Test
	public void test() {
		String url = "http://59.110.62.54/group1/M00/00/00/rBE3wF7KahqAP0msAABrFbzi4iA919.jpg";
		System.out.println("请求url:" + url);
		HttpClient client = HttpClients.createDefault(); // 创建默认http连接
		HttpGet get = new HttpGet(url);// 创建一个get请求
		try {
			HttpResponse response = client.execute(get);// 用http连接去执行get请求并且获得http响应
			HttpEntity entity = response.getEntity();// 从response中取到响实体
			String html = EntityUtils.toString(entity);// 把响应实体转成文本
			System.out.println("返回信息：" + html);
		} catch (Exception e) {
			System.out.println("wrong");
		}
	}

}
