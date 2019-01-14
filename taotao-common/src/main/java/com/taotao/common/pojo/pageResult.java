package com.taotao.common.pojo;

import java.util.List;

//分页结果类
public class pageResult {
	private Long total;
	private List<?> rows;
	public pageResult(Long totalNum,List<?> rows) {
		this.total = totalNum;
		this.rows = rows;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "pageResult [total=" + total + ", rows=" + rows + "]";
	}
	
}
