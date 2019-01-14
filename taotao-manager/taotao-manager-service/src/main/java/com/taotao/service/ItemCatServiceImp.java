package com.taotao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.UITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
@Service
public class ItemCatServiceImp implements ItemCatService{
	@Autowired
	private TbItemCatMapper mapper;
	@Override
	public List<UITreeNode> getNodeByPid(long pid) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(pid);
		List<TbItemCat> list = mapper.selectByExample(example);
		List<UITreeNode> nodes = new ArrayList<UITreeNode>();
		for(TbItemCat item :list) {
			UITreeNode node = new UITreeNode();
			node.setId(item.getId());
			node.setText(item.getName());
			System.out.println(item.getName());
			node.setState(item.getIsParent()? "closed":"open");
			nodes.add(node);
		}
		return nodes;
	}
	
}
