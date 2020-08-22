package org.ynu.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class TestSocketServer {

	//@Test
	public void test() throws IOException {
        
		String readline = null;
        String inTemp = null;
        //String outTemp = null;
        String turnLine = "\n";
        final String client = "Client:";
        final String server = "Server:";
        
        int port = 4000;
        //byte ipAddressTemp[] = {127, 0, 0, 1};
        //InetAddress ipAddress = InetAddress.getByAddress(ipAddressTemp);
        
        //首先直接创建serversocket
        ServerSocket serverSocket = new ServerSocket(port);  
        
        // 调用服务器的accept（）进行阻塞（程序会在这等待），s当有申请连接时会打开阻塞并返回一个socket
        Socket socket = serverSocket.accept();
        
        //创建三个流，系统输入流BufferedReader systemIn，socket输入流BufferedReader socketIn，socket输出流PrintWriter socketOut;
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        
        while(readline != "bye"){
            
            inTemp = socketIn.readLine();
            System.out.println(client + turnLine + inTemp);
            System.out.println(server);
            
            readline = systemIn.readLine();
            
            socketOut.println(readline);
            socketOut.flush();    //赶快刷新使Client收到，也可以换成socketOut.println(readline, ture)
            
            //outTemp = readline;
            
            //System.out.println(server);
            
        }
        
        systemIn.close();
        socketIn.close();
        socketOut.close();
        socket.close();
        serverSocket.close();
	}

}
