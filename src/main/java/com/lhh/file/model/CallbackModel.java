package com.lhh.file.model;

import java.util.Date;

/**
 * 
 * 数据在使用回调函数时使用
 * @createDate 2017年3月14日
 */
public class CallbackModel {

	private String path;
	private String eventType;
	private Date updateTime;
	
	
	private String text ;

	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
