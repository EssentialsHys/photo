package org.ynu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ynu.pojo.User;
import org.ynu.service.SSOService;
import org.ynu.util.cookie.CookieUtils;

import com.alibaba.dubbo.config.annotation.Reference;


/**
 * @Description: 用户单点登录系统
 * @author: hys 
 * @date: 2020年4月4日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/sso/user")
public class SSOUserController {
	
	@Reference
	private SSOService ssoService;
	
	/**
	 * @Description: 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public String register(User user, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return ssoService.register(user);
	}
	
	/**
	 * @Description: 用户登录
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(User user,HttpServletRequest request,HttpServletResponse response) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		Map<String,String> map = ssoService.login(user);
		//写cookie
		String token = map.get("token");//取出token
		if(StringUtils.isNoneBlank(token)) {
			CookieUtils.setCookie(request, response, "PHOTO-TOKEN-USER", token);
		}
		return map.get("result");
	}
	
	
	
}
