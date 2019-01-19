package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.pageResult;
import com.taotao.common.utils.ResultUtils;
import com.taotao.pojo.ItemParamPojo;

public interface ItemPatamsService {
	//查询所有的商品规格模板
	public abstract pageResult getAllParamsPlat(Integer page,Integer rows);
	//按照商品分类id查询是否有某模板的信息
	public abstract ResultUtils getPlatById(long cid);
	// 按照商品分类增加商品模板
	public abstract ResultUtils addPlatById(String data,long cid);
}
