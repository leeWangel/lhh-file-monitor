package com.lhh.file.job;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lhh.file.Constant;
import com.lhh.file.job.enums.FileOnOffEnum;
import com.lhh.file.service.LogsService;

/**
 * 文件任务调度
 * 
 * @createDate 2017年3月13日
 */
@Service
public class FileJob {
	
	private static final Logger log = LoggerFactory.getLogger(FileJob.class);
	private boolean isopen = true;// 文件是否启动监控程序开关
	private String listenerPath = Constant.filePath;
	private boolean isthreadrun = false;// 线程是否在运行
	private ResourceListener listener = null;
	@Resource LogsService logsService;

	/**
	 * 开始任务调度，监听
	 * 
	 */
	private void start() {
		if (!isopen) {
			log.info("未开启任务权限");
			return;
		}
		if (isthreadrun ||  listener != null) {// 线程运行情况
			log.warn("已有线程正在运行，未结束。本次不检测.");
			return;
		}
		synchronized (this) {
			if (isthreadrun) {// 线程没有在运行
				log.warn("已有线程正在运行，未结束。本次不检测.");
				return;
			}
			isthreadrun = true;
		}
		try {
			listener = new ResourceListener(listenerPath,logsService);
			listener.startListener();
		} catch (Exception e) {
			log.error("系统故障.未开启监控,请查看系统日志文件");
			e.printStackTrace();
			shutdown();
		}

	}

	/**
	 * 关闭后的对象无法重新启动
	 * 
	 * @throws IOException
	 */
	private void shutdown() {
		log.info("关闭文件监控记录");
		if( listener == null )return;
		try {
			listener.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			listener = null;
			isthreadrun = false;
		}
	}

	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}

	public void setListenerPath(String listenerPath) {
		if (StringUtils.isBlank(listenerPath)) {
			log.warn("检查地址不正确.");
			return;
		}
		File file = new File(listenerPath);
		if(!file.exists()){
			log.warn("检查地址不存在.");
			return;
		}
		this.listenerPath = listenerPath;
	}
	
	public void startOrStop(FileOnOffEnum e){
		if( e == null )return ;
		switch (e) {
		case ON:
			start();
			break;
		case OFF:
			shutdown();
			break;
		default:
			break;
		}
	}
	


}
