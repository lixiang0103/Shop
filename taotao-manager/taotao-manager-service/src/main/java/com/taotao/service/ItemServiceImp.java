package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.pageResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.ResultUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
@Service
public class ItemServiceImp implements ItemService{
	@Autowired
	private TbItemMapper mapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired 
	private TbItemParamItemMapper paramItemMapper;
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
	//实现商品信息和商品描述的新增
	@Override
	public ResultUtils insertItem(TbItem item,String desc,String itemParams) throws Exception {
		long itemID = IDUtils.ItemId();
		item.setId(itemID);
		//添加商品状态 1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//在同一个方法中调用，保证三个操作在同一个事务中
		mapper.insert(item);
		ResultUtils result = insertItemDesc(desc,itemID);
		ResultUtils resultParam = insertItemParams(itemID, itemParams);
		//如果不成功，不进行回滚，由于Spring对事务进行了统一管理，而是抛出相应的异常
		if(result.getStatus() != 200 || resultParam.getStatus()!=200) {
			throw new Exception();
		}
		return ResultUtils.ok();
	}
	//当在一个service中要调用其它的mapper完成某一功能，往往重新写一个方法
	private ResultUtils insertItemDesc(String desc,long itemID) {
		TbItemDesc descItem = new TbItemDesc();
		descItem.setItemId(itemID);
		descItem.setItemDesc(desc);
		descItem.setCreated(new Date());
		descItem.setUpdated(new Date());
		itemDescMapper.insert(descItem);
		return ResultUtils.ok();
	}
	private ResultUtils insertItemParams(long itemID, String paramData) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemID);
		itemParamItem.setParamData(paramData);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		paramItemMapper.insert(itemParamItem);
		return ResultUtils.ok();
	}
}
