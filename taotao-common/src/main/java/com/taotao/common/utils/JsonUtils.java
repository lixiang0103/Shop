package com.taotao.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	//定义一个jackson对象
	private static final ObjectMapper json = new ObjectMapper();
	//将对象转化为json字符串
	public static String objectToJson(Object obj) {
		try {
			String str = json.writeValueAsString(obj);
			return str;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
