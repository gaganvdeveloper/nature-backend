package com.gbss.nature.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import jakarta.annotation.Resource;

@Service
public class CloudinaryService {
	
	@Resource
	Cloudinary cloudinary;
	
	public String uploadFile(MultipartFile file, String folderName) {
		Map<Object, Object> folders = new HashMap<>();
		folders.put("folder", folderName);
		try {
			Map<?,?> uploadedFile = cloudinary.uploader().upload(file.getBytes(), folders);
			String publicId =(String) uploadedFile.get("public_id");
			return cloudinary.url().secure(true).generate(publicId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
}
