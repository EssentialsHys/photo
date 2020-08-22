package org.ynu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ynu.pojo.Order;
import org.ynu.service.OrderService;
import org.ynu.util.fdfs.FastDFSClient;

import com.alibaba.dubbo.config.annotation.Reference;


/**
 * @Description: 订单系统
 * @author: hys 
 * @date: 2020年4月24日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/order")
public class OrderController {

	@Reference
	private OrderService orderService;
	
	@Value("${FASTDFS_SERVER}")
	private String FASTDFS_SERVER;
	
	/**
	 * @Description: 添加订单
	 * @param order
	 * @param pname
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addOrder(Order order,String pname,@CookieValue("PHOTO-TOKEN-USER")String token,List<String> names, HttpServletResponse response, HttpServletRequest request){
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.addOrder(order,pname,token,names);
	}
	
	/**
	 * @Description: 完成订单
	 * @param uploadFiles
	 * @param oid
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String updateOrder(MultipartFile[] uploadFiles, Long oid, @CookieValue("PHOTO-TOKEN-PHOTOGRAPHER")String token, HttpServletResponse response, HttpServletRequest request){
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		List<String> urls = new ArrayList<String>();
		FastDFSClient client = new FastDFSClient("classpath:client.conf");
		
		for(MultipartFile uploadFile:uploadFiles) {
			String fileName = uploadFile.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".")+1);
			try {
				String url = FASTDFS_SERVER + client.uploadFile(uploadFile.getBytes(), ext);
				urls.add(url);
			} catch (IOException e) {
				return "fail";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return orderService.completeOrder(oid,token,urls);
	}
	
	/**
	 * @Description: 查询订单
	 * @param pname
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public List<Order> queryOrder(String pname, HttpServletResponse response, HttpServletRequest request){
		//response.setHeader("Access-Control-Allow-Origin","*");
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.queryOrder(pname);
	}
	
	/**
	 * @Description: 开始订单
	 * @param lat
	 * @param lng
	 * @param oid
	 * @return
	 */
	@RequestMapping(value="/start",method=RequestMethod.GET)
	@ResponseBody
	public String startOrder(double lat, double lng, Long oid, @CookieValue("PHOTO-TOKEN-PHOTOGRAPHER")String token, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin","*");
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.startOrder(lat, lng, oid, token);
	}
}
