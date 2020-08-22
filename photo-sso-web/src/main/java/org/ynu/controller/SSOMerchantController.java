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
import org.ynu.pojo.Merchant;
import org.ynu.service.SSOService;
import org.ynu.util.cookie.CookieUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @Description: 商家单点登录系统
 * @author: hys 
 * @date: 2020年4月4日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/sso/merchant")
public class SSOMerchantController {
	
	@Reference
	private SSOService ssoService;
	
	/**
	 * @Description: 商家注册
	 * @param merchant
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public String register(Merchant merchant, HttpServletResponse response, HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return ssoService.register(merchant);
	}
	
	/**
	 * @Description: 商家登录
	 * @param merchant
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(Merchant merchant,HttpServletRequest request,HttpServletResponse response) {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		Map<String,String> map = ssoService.login(merchant);
		//写cookie
		String token = map.get("token");//取出token
		if(StringUtils.isNoneBlank(token)) {
			CookieUtils.setCookie(request, response, "PHOTO-TOKEN-MERCHANT", token);
		}
		return map.get("result");
	}
	
	
}
