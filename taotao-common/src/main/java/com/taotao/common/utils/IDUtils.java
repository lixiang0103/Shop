package com.taotao.common.utils;

import java.util.Random;

public class IDUtils {
	//生成图片的名称
	public static String getPictureName() {
		Long millis = System.currentTimeMillis();
		int end3 = new Random().nextInt(999);
		String str = millis+String.format("%03d", end3);
		return str;
	}
	//生成商品的id (long 型)
	public static Long ItemId() {
		long millis = System.currentTimeMillis();
		int end = new Random().nextInt(999);
		String str = millis + String.format("%02d", end); //在该数据的前面添加两位数字
		Long id = new Long(str);  //使用封装类型，将字符串转换为具体的数据
		return id;
	}
	
}
