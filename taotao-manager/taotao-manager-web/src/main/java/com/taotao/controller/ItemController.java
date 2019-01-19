package com.taotao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taotao.common.pojo.pageResult;
import com.taotao.common.utils.ResultUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemPatamsService;
import com.taotao.service.ItemService;
import com.taotao.service.ItemServiceImp;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	//查询所有的商品
	@RequestMapping(value="/item/list.action")
	@ResponseBody
	public pageResult getAllItem(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="30") Integer rows){
		System.out.println(page+","+rows);
		pageResult result = itemService.getAllItems(page,rows);
		System.out.println(result);
		return result;
	}
	//增加商品信息，使用pojo接收表单数据(要求表单中的字段名与pojo保持一致)
	@RequestMapping(value="/item/save",method = RequestMethod.POST)
	@ResponseBody
	public ResultUtils addItem(TbItem item,String desc,String itemParams) throws Exception {
		//在添加商品信息时，需要添加商品的基本信息，商品描述以及商品的规格信息
		return itemService.insertItem(item,desc,itemParams);
	}
}

