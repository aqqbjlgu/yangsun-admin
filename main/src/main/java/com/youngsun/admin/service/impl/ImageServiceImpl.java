package com.youngsun.admin.service.impl;

import com.youngsun.admin.service.ImageService;
import com.youngsun.admin.util.Result;
import com.youngsun.common.exception.BudinessRuntimeException;
import com.youngsun.common.util.FastDFSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	@Value("${FDFS_CLIENT_URL}")
	private String FDFS_CLIENT_URL;
	private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
	@Override
	public Result upload(MultipartFile imageFile) throws Exception {
		if(imageFile.isEmpty()){
			log.error("500", "图片不能为空");
			throw new BudinessRuntimeException("图片不能为空", 500);
		}
		String[] url;
		try {
			log.error("FDFS_CLIENT_URL ======{}" , FDFS_CLIENT_URL);
			FastDFSClient client = new FastDFSClient(FDFS_CLIENT_URL);
			String originalFilename = imageFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.indexOf(".")+1);
			url = client.uploadFile(imageFile.getBytes(), extName);
		} catch (Exception e) {
			log.error("500",  e);
			throw new BudinessRuntimeException("图片上传出错", 500);
		}
		return Result.ok(IMAGE_SERVER_BASE_URL+"dfs/"+url[0]+"/"+url[1]);
	}

}
