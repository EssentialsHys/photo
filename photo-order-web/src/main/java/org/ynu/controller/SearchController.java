package org.ynu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ynu.pojo.Photographer;
import org.ynu.service.OrderService;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @Description: 查询控制器
 * @author: hys 
 * @date: 2020年4月1日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/query")
public class SearchController {
	
	@Reference
	private OrderService orderService;
	
	/**
	 * @Description: 根据名字查找摄影师
	 * @param pname
	 * @return
	 */
	@RequestMapping(value="/photographer/name",method=RequestMethod.GET)
	@ResponseBody
	public Photographer queryPhotographerByName(String pname, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.queryPhotographerByName(pname);
	}
	
	/**
	 * @Description: 根据学校查找摄影师 
	 * @param school
	 * @return
	 */
	@RequestMapping(value="/photographer/school",method=RequestMethod.GET)
	@ResponseBody
	public List<Photographer> queryPhotographerBySchool(String school, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.queryPhotographerBySchool(school);
	}
	
	/**
	 * @Description: 根据价格区间查找摄影师 
	 * @param low
	 * @param high
	 * @return
	 */
	@RequestMapping(value="/photographer/price",method=RequestMethod.GET)
	@ResponseBody
	public List<Photographer> queryPhotographerByPrice(int low, int high, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.queryPhotographerByPrice(low, high);
	}
	
	/**
	 * @Description: 根据风格查找摄影师
	 * @param style
	 * @return
	 */
	@RequestMapping(value="/photographer/style",method=RequestMethod.GET)
	@ResponseBody
	public List<Photographer> queryPhotographerByStyle(String style, HttpServletResponse response, HttpServletRequest request){
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return orderService.queryPhotographerByStyle(style);
	}
	
	// 下面的部分用redis模拟session
	/*@RequestMapping("/userInf")
	@ResponseBody
	public User userInf(@RequestParam(required=false)String uname) {
		if(uname!=null) {
			return searchService.userInf(uname);
		}
		String key = "uname";
		String info = jedisClient.get(key);
		
		// 有数据查缓存
		if(StringUtils.isNoneBlank(info)) {
			jedisClient.expire(key, 60);
			
		}
		
	}
	
	@RequestMapping("/userInf")
	@ResponseBody
	public Photographer photographerInf(@RequestParam(required=false)String pname) {
		return searchService.photographerInf(pname);
	}*/
	

}
