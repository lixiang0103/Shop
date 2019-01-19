package com.taotao.service;

import com.taotao.common.utils.ResultUtils;

public interface IttemParamItemService {
	//根据商品id查询商品的规格信息
	public abstract String getParamsByid(long itemID);
}
