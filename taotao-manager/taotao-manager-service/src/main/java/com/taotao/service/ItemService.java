package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.pageResult;
import com.taotao.common.utils.ResultUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public interface ItemService {
	//根据id查询商品信息
	public abstract TbItem getItemById(Long id);
	//查询所有商品信息
	public abstract pageResult getAllItems(int page,int rows);
	//增加商品
	public abstract ResultUtils insertItem(TbItem item,String desc,String itemParams) throws Exception;
}
