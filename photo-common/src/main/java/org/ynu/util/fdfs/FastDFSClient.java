package org.ynu.util.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * @Description: 作为FastDFSClient上传图片工具类
 * @author: hys 
 * @date: 2020年4月7日
 */
public class FastDFSClient {
	
	private String confPath;
	
	public FastDFSClient(String confPath) {
		this.confPath = confPath;
		try {
			if(confPath.indexOf("classpath")!=-1) {
				// client.conf
				String path = confPath.substring(confPath.indexOf("classpath")+10);
				// file:/D:/java_workspace/photo-portal/photo-portal-service/target/classes/client.conf
				URL resource = FastDFSClient.class.getClassLoader().getResource(path);
				// /D:/java_workspace/photo-portal/photo-portal-service/target/classes/client.conf
				String path2 = resource.getPath();
				//根据获取的绝对路径加载配置文件
				ClientGlobal.init(path2);
			}else { //绝对路径
				ClientGlobal.init(confPath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	public String uploadFile(String fileName,String ext) throws Exception {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getTrackerServer();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		String[] infos = storageClient.upload_file(fileName, ext, null);
		
		return infos[0]+"/"+infos[1];
	}
	
	public String uploadFile(byte[] fileData,String ext) throws Exception {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getTrackerServer();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		String[] infos = storageClient.upload_file(fileData, ext, null);
		
		return infos[0]+"/"+infos[1];
	}
}
