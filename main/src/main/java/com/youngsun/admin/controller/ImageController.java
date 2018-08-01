package com.youngsun.admin.controller;

import com.youngsun.admin.service.ImageService;
import com.youngsun.admin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping("/pic/upload")
	public Result upload(@RequestParam("image") MultipartFile uploadFile) throws Exception {
		Result uploadImageResult = imageService.upload(uploadFile);
		return uploadImageResult;
	}

	@PostMapping("/pic/ckUpload")
	public Map<String, String> ckUpload(@RequestParam("file") MultipartFile uploadFile) throws Exception {
		Result uploadImageResult = imageService.upload(uploadFile);
		Map<String, String> returnMap = new HashMap<>();
		returnMap.put("link", uploadImageResult.getData().toString());
		return returnMap;
	}
}
