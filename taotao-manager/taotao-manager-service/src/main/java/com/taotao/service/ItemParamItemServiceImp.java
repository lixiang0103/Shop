package com.taotao.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.ResultUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;

@Service
public class ItemParamItemServiceImp implements IttemParamItemService{
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public String getParamsByid(long itemID) {
		TbItemParamItemExample exmaple = new TbItemParamItemExample();
		Criteria criteria = exmaple.createCriteria();
		criteria.andItemIdEqualTo(itemID);
		//取出大文本信息
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(exmaple);
		if(list==null||list.size()==0) {
			return "";
		}
		String jsonData = list.get(0).getParamData();
		//将json转换为html
		List<Map> listData = JsonUtils.jsonToList(jsonData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for(Map map:listData) {
			sb.append("			<tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			@SuppressWarnings("unchecked")
			List<Map> paramMap = (List<Map>)map.get("params");
			System.out.println(paramMap);
			for(Map param:paramMap) {
				sb.append("			<tr>\n");
				System.out.println(map.get("k"));
				sb.append("            <td class=\"tdTitle\">"+param.get("k")+"</td>\n");
				System.out.println(map.get("v"));
				sb.append("            <td >"+param.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}
	
}
