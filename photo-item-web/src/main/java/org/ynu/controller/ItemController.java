package org.ynu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ynu.pojo.Item;
import org.ynu.service.ItemService;
import org.ynu.service.PictureService;
import org.ynu.util.fdfs.FastDFSClient;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @Description: 
 * @author: hys 
 * @date: 2020年6月5日
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Reference
	private ItemService itemService;
	
	@Reference
	private PictureService pictureService;
	
	/**
	 * @Description: 添加商品
	 * @param item
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addItem(Item item, @CookieValue("PHOTO-TOKEN-MERCHANT")String token, MultipartFile uploadFile, HttpServletResponse response, HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		byte[] bytes;
		try {
			bytes = uploadFile.getBytes();
		} catch (IOException e1) {
			return "IOException";
		}
		String fileName = uploadFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		return itemService.addItem(item, token, bytes, ext);	
	}
	
	
	/**
	 * @Description: 查询所有商品
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public List<Item> queryAllItem(HttpServletResponse response, HttpServletRequest request){
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		return itemService.queryAllItem();
	}
	
	
}
