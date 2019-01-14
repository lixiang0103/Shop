package com.taotao.common.pojo;
//EasyUI Tree的结点结构
public class UITreeNode {
	private long id;
	private String Text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "UITreeNode [id=" + id + ", Text=" + Text + ", state=" + state + "]";
	}
	
	
}
