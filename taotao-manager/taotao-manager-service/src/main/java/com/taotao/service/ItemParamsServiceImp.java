package com.taotao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.pageResult;
import com.taotao.common.utils.ResultUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.ItemParamPojo;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamItemExample;
@Service
public class ItemParamsServiceImp implements ItemPatamsService{
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public pageResult getAllParamsPlat(Integer page,Integer rows) {
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		PageInfo<TbItemParam> info = new PageInfo<>(list);
		List<ItemParamPojo> pojoList = new ArrayList<ItemParamPojo>();
		for(TbItemParam itemParam:list) {
			ItemParamPojo pojo = new ItemParamPojo();
			//生成查询条件
			TbItemCatExample example = new TbItemCatExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(itemParam.getId());
			String cName = itemCatMapper.selectByExample(example).get(0).getName();
			pojo.setId(itemParam.getId());
			pojo.setItemCatId(itemParam.getItemCatId());
			pojo.setItemCatName(cName);
			pojo.setParamData(itemParam.getParamData());
			pojo.setCreated(itemParam.getCreated());
			pojo.setUpdated(itemParam.getUpdated());
			pojoList.add(pojo);
		}
		pageResult result = new pageResult(info.getTotal(), pojoList);
		return result;
	}
	//按照商品分类id查询是否有某模板的信息
	@Override
	public ResultUtils getPlatById(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		TbItemParamExample.Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		System.out.println(list.get(0).getParamData());
		if(list!=null&&list.size() > 0) {
			return ResultUtils.ok(list.get(0).getParamData());
		}
		return ResultUtils.ok();
	}
	//增加商品模板
	@Override
	public ResultUtils addPlatById(String data, long cid) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(data);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return ResultUtils.ok();
	}
	
}
