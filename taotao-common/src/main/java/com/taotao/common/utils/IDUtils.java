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
}
