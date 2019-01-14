package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.pageResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
@Service
public class ItemServiceImp implements ItemService{
	@Autowired
	private TbItemMapper mapper;
	@Override
	//根据商品ID查询商品信息
	public TbItem getItemById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public pageResult getAllItems(int page, int rows) {
		TbItemExample example = new TbItemExample();
		//设置分页
		PageHelper.startPage(page, rows);
		List<TbItem> list = mapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		pageResult result = new pageResult(total, list);
		System.out.println(result);
		return result;
	}
	
	
}
