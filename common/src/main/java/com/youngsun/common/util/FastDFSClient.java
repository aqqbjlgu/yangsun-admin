package com.youngsun.common.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.FileNotFoundException;
import java.io.IOException;
public class FastDFSClient {
	
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient storageClient = null;

	public FastDFSClient(String conf) throws FileNotFoundException, IOException, MyException {
		if(conf.contains("classpath:")){
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageClient = new StorageClient(trackerServer, storageServer);
	}
	
	public String[] uploadFile(String fileName, String extName, NameValuePair[] metas) throws IOException, MyException{
		String[] result = storageClient.upload_file(fileName, extName, metas);
		return result;
	}
	
	public String[] uploadFile(String fileName) throws IOException, MyException{
		String[] result = storageClient.upload_file(fileName, null, null);
		return result;
	}
	
	public String[] uploadFile(String fileName, String extName) throws IOException, MyException{
		String[] result = storageClient.upload_file(fileName, extName, null);
		return result;
	}
	
	public String[] uploadFile(byte[] file, String extName, NameValuePair[] metas) throws IOException, MyException{
		String[] result = storageClient.upload_file(file, extName, metas);
		return result;
	}
	
	public String[] uploadFile(byte[] file) throws IOException, MyException{
		String[] result = storageClient.upload_file(file, null, null);
		return result;
	}
	
	public String[] uploadFile(byte[] file, String extName) throws IOException, MyException{
		String[] result = storageClient.upload_file(file, extName, null);
		return result;
	}

}
