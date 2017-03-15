package com.lhh.file.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhh.file.manager.SqlManager;
import com.lhh.file.model.CallbackModel;
import com.lhh.file.model.Logs;
import com.lhh.file.service.CallbackService;
import com.lhh.file.service.LogsService;
import com.lhh.file.utils.UtilsString;

/**
 * 日志记录
 * @createDate 2017年3月14日
 */
@Service("logsService")
public class LogsServiceImpl implements LogsService,CallbackService{

	@Resource private SqlManager sqlManager;
	
	

	public void callback(CallbackModel c) {
		System.out.println("记录数据"+c.getEventType());
		
		Logs logs = new Logs();
		logs.setIdStr(UtilsString.toStringUUID());
		logs.setF_type_ot("文件监控");
		logs.setF_type_ft(c.getEventType());
		logs.setInsert_time(c.getUpdateTime());
		logs.setContent(c.getText());
		//logs.setUse_user(use_user);   读取登录用户信息
		
		sqlManager.insertLog(logs);
	}


}

