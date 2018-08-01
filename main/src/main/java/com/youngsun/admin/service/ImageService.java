package com.youngsun.admin.service;

import com.youngsun.admin.util.Result;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	public Result upload(MultipartFile imageFile) throws Exception;

}
