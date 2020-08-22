package org.ynu.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ynu.service.VIPService;
import org.ynu.util.fdfs.FastDFSClient;

/**
 * @Description: 会员服务实现类
 * @author: hys 
 * @date: 2020年6月6日
 */
@Service
@Transactional
public class VIPServiceImpl implements VIPService{
	
	@Value("${FASTDFS_SERVER}")
	private String FASTDFS_SERVER;
	
	
	@Override
	public String changeClothes(String fileName1, String fileName2) {
		
//		FastDFSClient client = new FastDFSClient("classpath:client.conf");
//		String url1 = null;
//		String url2 = null;
//		
//		try {
//			url1 = client.uploadFile(file1, ext1);
//			url2 = client.uploadFile(file2, ext2);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		Socket socket = null;
		String str = null;
        String Code_Adress = "32021p31z3.wicp.vip";
        try {
            socket = new Socket(Code_Adress,29662);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            
            //outputStream.write((url1+" "+url2).getBytes());
            outputStream.write((fileName1+" "+fileName2).getBytes());
            
            int len = inputStream.read(bytes);
            str = new String(bytes,0,len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;	
	}


	@Override
	public String changeBackground(byte[] bytes1,byte[] bytes2,String ext1, String ext2) {
		FastDFSClient client = new FastDFSClient("classpath:client.conf");
		String url1 = null;
		String url2 = null;
		
		try {
			url1 = client.uploadFile(bytes1, ext1);
			url2 = client.uploadFile(bytes2, ext2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Socket socket = null;
		String str = null;
        String Code_Adress = "coutugp.vicp.io";
        try {
            socket = new Socket(Code_Adress,57723);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            
            outputStream.write((FASTDFS_SERVER+url1+" "+FASTDFS_SERVER+url2).getBytes());
                        
            int len = inputStream.read(bytes);
            str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
	}

}
