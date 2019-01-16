package com.taotao.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.UPloadPicture;
import com.taotao.service.UPloadPictureImp;

@Controller
public class UPloadController {
	@Autowired
	private UPloadPicture uploadPicture;
	@RequestMapping(value="/pic/upload")
	@ResponseBody
	public String uploadPicture(@RequestParam("uploadFile") MultipartFile file,HttpServletRequest request) {
		System.out.println(file);
		Map<String,String> result = new HashedMap();
		String realUploadPath=request.getSession().getServletContext().getRealPath("/")+"image";
		System.out.println("realUploadPath"+realUploadPath);
		//得到图片上传后的相对地址
		try {
			String imageUrl= uploadPicture.uploadFile(file, realUploadPath);
			//为了保证兼容性，需要将map对象转换为json字符串
			result.put("error", "0");
			result.put("url", imageUrl);
			return JsonUtils.objectToJson(result);
		} catch (IOException e) {
			result.put("error", "1");
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
	}
}
