package com.taotao.service;

import java.awt.MultipleGradientPaint;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UPloadPicture {
	//上传文件到指定目录
	public abstract String uploadFile(MultipartFile file,String filePath) throws IOException;
}
