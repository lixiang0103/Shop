package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.UITreeNode;

public interface ItemCatService {
	//根据父节点ID查询商品条目
	public abstract List<UITreeNode> getNodeByPid(long pid);
}
