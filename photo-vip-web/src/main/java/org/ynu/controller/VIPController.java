package org.ynu.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ynu.service.VIPService;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @Description: 会员控制器
 * @author: hys 
 * @date: 2020年6月7日
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/vip")
public class VIPController {
	
	@Reference
	private VIPService vipService;
	
	
	@RequestMapping(value="/cloth",method=RequestMethod.POST)
	@ResponseBody
	public String cloth(MultipartFile file1, MultipartFile file2, HttpServletResponse response, HttpServletRequest request) throws IOException {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		//byte[] bytes1 = file1.getBytes();
		//byte[] bytes2 = file2.getBytes();
		
		String fileName1 = file1.getOriginalFilename();
		//String ext1 = fileName1.substring(fileName1.lastIndexOf(".")+1);
		System.out.println(fileName1);
		String fileName2 = file2.getOriginalFilename();
		//String ext2 = fileName1.substring(fileName2.lastIndexOf(".")+1);
		System.out.println(fileName2);
		//vipService.ChangeClothes(bytes1,bytes2,ext1,ext2);
		return vipService.changeClothes(fileName1,fileName2);
	}
	
	
	@RequestMapping(value="/bground",method=RequestMethod.POST)
	@ResponseBody
	public String bground(MultipartFile file1, MultipartFile file2, HttpServletResponse response, HttpServletRequest request) throws IOException {
		//response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		byte[] bytes1 = file1.getBytes();
		byte[] bytes2 = file2.getBytes();
		
		String fileName1 = file1.getOriginalFilename();
		String ext1 = fileName1.substring(fileName1.lastIndexOf(".")+1);
		
		String fileName2 = file2.getOriginalFilename();
		String ext2 = fileName1.substring(fileName2.lastIndexOf(".")+1);
		
		return vipService.changeBackground(bytes1, bytes2, ext1, ext2);
	}
	
	@RequestMapping(value="/aaaa",method=RequestMethod.GET)
	@ResponseBody
	public String aaaa() throws InterruptedException {
		Thread.sleep(5000);
		return "http://59.110.62.54/group1/M00/00/00/rBE3wF7vYWyASxW6AAAT6_nyPa8980.jpg";
	}
	
	@RequestMapping(value="/bbbb",method=RequestMethod.GET)
	@ResponseBody
	public String bbbb() throws InterruptedException {
		Thread.sleep(5000);
		return "http://59.110.62.54/group1/M00/00/01/rBE3wF7va8aAIdiDAAO9nIOfxSk086.jpg";
	}
	
	@RequestMapping(value="/cccc",method=RequestMethod.POST)
	@ResponseBody
	public List<String> ccc(String context){
		List<String> res = new LinkedList<String>();
		Socket socket = null;
        String Code_Adress = "coutugp.vicp.io";
        try {
            socket = new Socket(Code_Adress,27290);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            writeTxt("/usr/local/c.txt",context);
            byte[] data = null;
            try {
                FileInputStream fis = new FileInputStream(new File("/usr/local/c.txt"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
     
                int len;
                byte[] buffer = new byte[1024];
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
     
                data = baos.toByteArray();
  
                fis.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            outputStream.write(data);
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            str = str.replace("[", "");
        	str = str.replace("]", "");
        	str = str.replace("'", "");
        	
        	String[] words = str.split(",");
    		for(String word:words) {
    			res.add(word);
    		}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
	}
	
	
	
	/**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath,String content){    
       FileOutputStream fileOutputStream = null;
       File file = new File(txtPath);
       try {
           if(file.exists()){
               //判断文件是否存在，如果不存在就新建一个txt
               //file.createNewFile();
        	   file.delete();
           }
           fileOutputStream = new FileOutputStream(file);
           fileOutputStream.write(content.getBytes());
           fileOutputStream.flush();
           fileOutputStream.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
	
	
	
	
}
