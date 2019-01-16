package com.taotao.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.IDUtils;

@Service
public class UPloadPictureImp implements UPloadPicture{

	@Override
	public String uploadFile(MultipartFile file, String filePath) throws IOException {
		//生成新的文件名
		String fileName = IDUtils.getPictureName();
		String oldName = file.getOriginalFilename();
		fileName = fileName +oldName.substring(oldName.lastIndexOf("."));
		//生成输出地址URL
		String outputPath=filePath + "\\"+fileName;
		file.transferTo(new File(outputPath));
		String resultPath = "/image" + "/"+  fileName;
		return resultPath;
	}
	
}
