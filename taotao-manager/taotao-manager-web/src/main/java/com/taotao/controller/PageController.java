package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//用于实现页面的跳转
@Controller
public class PageController {
	//用于跳转到后台管理系统的首页
	@RequestMapping(value="/")
	public String pageIndex() {
		return "index";
	}
	//页面的跳转
	@RequestMapping(value="/{page}")
	public String pageShow(@PathVariable String page) {
		return page;
	}
}
