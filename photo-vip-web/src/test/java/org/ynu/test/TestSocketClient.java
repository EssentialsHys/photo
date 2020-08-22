package org.ynu.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestSocketClient {
	
	//@Test
	public void test() {
		Socket socket = null;
        String Code_Adress = "32021p31z3.wicp.vip";
        try {
            socket = new Socket(Code_Adress,29662);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            
            outputStream.write("hello".getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//@Test
	public void test2() throws IOException {
		Socket socket = null;
        String Code_Adress = "32021p31z3.wicp.vip";
        try {
            socket = new Socket(Code_Adress,29662);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            String pic1 = "000001_0.jpg";
            String pic2 = "001567_1.jpg";
            outputStream.write((pic1+" "+pic2).getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//@Test
	public void test3() {
		Socket socket = null;
        String Code_Adress = "32021p31z3.wicp.vip";
        try {
            socket = new Socket(Code_Adress,29662);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            String pic1 = "000001_0.jpg";
            String pic2 = "001567_1.jpg";
            outputStream.write(pic1.getBytes());
            outputStream.write(pic2.getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void test4() {
		Socket socket = null;
		List<String> res = new LinkedList<String>();
        String Code_Adress = "coutugp.vicp.io";
        try {
            socket = new Socket(Code_Adress,27290);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            writeTxt("C:\\Users\\Administrator\\Desktop\\2.txt","昆明春城一定是骗人的晚上坐着看文献，穿毛衣都冷的贴面膜也凉的说好的春城呢允悲昆明·云南大学呈贡校区");
            byte[] data = null;
            try {
                FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\2.txt"));
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
            System.out.println(str);
            str = str.replace("[", "");
        	str = str.replace("]", "");
        	str = str.replace("'", "");
        	str = str.replace(" ", "");
        	
        	String[] words = str.split(",");
    		for(String word:words) {
    			System.out.println(word);
    			res.add(word);
    		}
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    
    //@Test
    public void aaa() {
    	//writeTxt("C:\\Users\\Administrator\\Desktop\\2.txt","气死我了，这破玩意老连不上");
    	String s = "['焦虑','害怕','']";
    	s = s.replace("[", "");
    	s = s.replace("]", "");
    	s = s.replace("'", "");
    	System.out.println(s);
    	
		String[] words = s.split(",");
		for(String word:words) {
			System.out.println(word);
		}
    }
}
