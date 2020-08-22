package org.ynu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ynu.service.PictureService;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @Description: 上传图片的控制器
 * @author: hys 
 * @date: 2020年4月9日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/pics")
public class PictureController {
	
	@Reference
	private PictureService pictureService;
	
	@Value("${FASTDFS_SERVER}")
	private String FASTDFS_SERVER;
	
	/**
	 * @Description: 上传图片
	 * @param uploadFiles
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public String uploadFile(MultipartFile[] uploadFiles,@CookieValue("PHOTO-TOKEN-PHOTOGRAPHER")String token, HttpServletResponse response, HttpServletRequest request){	
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		for(MultipartFile uploadFile:uploadFiles) {
			byte[] bytes;
			try {
				bytes = uploadFile.getBytes();
			} catch (IOException e1) {
				return "IOException";
			}
			String fileName = uploadFile.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".")+1);
			String answer = pictureService.upload(bytes, ext, token);
			if(!"success".equals(answer)) {
				return answer;
			}
		}
		return "success";
	}
	
	/**
	 * @Description: 查找摄影师的作品
	 * @param pname
	 * @return
	 */
	@RequestMapping(value="/inf",method=RequestMethod.GET)
	@ResponseBody
	public List<String> queryUserPics(String pname, HttpServletResponse response, HttpServletRequest request){
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		return pictureService.queryUserPics(pname);
	}
}
