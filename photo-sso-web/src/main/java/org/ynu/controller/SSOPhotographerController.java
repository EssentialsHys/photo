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
import org.ynu.pojo.Photographer;
import org.ynu.service.SSOService;
import org.ynu.util.cookie.CookieUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @Description: 摄影师单点登录系统
 * @author: hys 
 * @date: 2020年4月4日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/sso/photographer")
public class SSOPhotographerController {
	
	@Reference
	private SSOService ssoService;
	
	
	/**
	 * @Description: 摄影师注册
	 * @param photographer
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public String register(Photographer photographer, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return ssoService.register(photographer);
	}
	
	/**
	 * @Description: 摄影师登录
	 * @param photographer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(Photographer photographer,HttpServletRequest request,HttpServletResponse response) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Methods","POST");
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		Map<String,String> map = ssoService.login(photographer);
		//写cookie
		String token = map.get("token");//取出token
		if(StringUtils.isNoneBlank(token)) {
			CookieUtils.setCookie(request, response, "PHOTO-TOKEN-PHOTOGRAPHER", token);
		}
		return map.get("result");
	}
	
	
}
