package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.pageResult;
import com.taotao.common.utils.ResultUtils;
import com.taotao.pojo.ItemParamPojo;
import com.taotao.service.ItemPatamsService;
import com.taotao.service.IttemParamItemService;

@Controller
public class ItemParamsController {
	@Autowired
	private ItemPatamsService itemPatamsService;
	@Autowired
	private IttemParamItemService paramItemService;
	@RequestMapping(value="/item/param/list")
	@ResponseBody
	public pageResult getParamList(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="30") Integer rows){
		pageResult result = itemPatamsService.getAllParamsPlat(page, rows);
		return result;
	}
	//根据商品类别id查询商品规格模板
	@RequestMapping(value="/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public ResultUtils selectPlatByCid(@PathVariable Long cid) {
		System.out.println(cid);
		ResultUtils result  =  itemPatamsService.getPlatById(cid);
		return result;
	}
	//按照分类增加商品规格模板
	@RequestMapping(value="/item/param/save/{cid}")
	@ResponseBody
	public ResultUtils addPlatByCid(@PathVariable Long cid, String paramData) {
		return itemPatamsService.addPlatById(paramData, cid);
	}
	//展示商品的规格信息
	@RequestMapping(value="/item/param/query/itemid/{id}")
	public String getParamsByItemId(@PathVariable long id,Model model) {
		String params = paramItemService.getParamsByid(id);
		model.addAttribute("itemParam", params);
		return "item";
	}
}
