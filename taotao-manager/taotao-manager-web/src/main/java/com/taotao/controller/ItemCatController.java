package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.UITreeNode;
import com.taotao.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	//返回easyUI tree当前结点下的子节点
	@RequestMapping(value="item/cat/list")
	@ResponseBody
	public List<UITreeNode> getTreeNodes(@RequestParam(value="id", defaultValue="0") Long parentId){
		List<UITreeNode> list = new ArrayList<UITreeNode>();
		list = itemCatService.getNodeByPid(parentId);
		return list;
	}
}
