package com.lhh.file.job;

import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhh.file.model.CallbackModel;
import com.lhh.file.service.CallbackService;

/**
 * 监听文件变动
 * 
 * @createDate 2017年2月24日
 */
class ResourceListener {
	private static final Logger log = LoggerFactory.getLogger(ResourceListener.class);
	//池
	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
	private WatchService ws;
	private CallbackService callbackService;
	//监控地址
	private String listenerPath;
	//监控关闭开关（防止使用者在使用时未正常关闭）
	private static boolean isShutdown = false;
	//监控类型
	private Kind<?>[] keys = {
			StandardWatchEventKinds.ENTRY_MODIFY,//更新
			StandardWatchEventKinds.ENTRY_DELETE,//创建
			StandardWatchEventKinds.ENTRY_CREATE,//删除
			}; 

	public ResourceListener(String path,CallbackService callbackService) {
		listenerPath = path;
		this.callbackService = callbackService;
	}

	public void startListener() throws IOException {
		log.info("正开启文件监控...");
		log.info("初始化数据");
		
		isShutdown = true;
		ws = FileSystems.getDefault().newWatchService();
		fixedThreadPool.execute(new Listner());
		Path p = Paths.get(listenerPath);
		p.register(ws,keys);

		
		log.info("开始监控");
	}

	public void shutdown() {
		isShutdown = false;
		if( fixedThreadPool!= null ){
			fixedThreadPool.shutdown();
		}
		fixedThreadPool = null;
		
		try {
			ws.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("监控已关闭");
		}
	}

	public static void main(String[] args) throws IOException {
		new ResourceListener("E:/aaa",null).startListener();
	}

	class Listner implements Runnable {
		public Listner() {
		}

		public void run() {
			try {
				while (true) {
					if (!isShutdown) {
						break;
					}

					WatchKey watchKey = ws.take();
					List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
					for (WatchEvent<?> event : watchEvents) {
						// TODO 根据事件类型采取不同的操作。。。。。。。
						String path = "[" + listenerPath + "/"+ event.context();
						String eventType = toEventType(event.kind());
						CallbackModel c = new CallbackModel();
						c.setPath(path);
						c.setEventType(eventType);
						
						
						/***
						 * 
						 * 处理文件信息
						 * 
						 * 
						 * 
						 */
						
						
						
						
						
						c.setUpdateTime(new Date());
						callbackService.callback(c);//回调进入数据记录操作
					}
					watchKey.reset();
				}
			} catch (final ClosedWatchServiceException | InterruptedException e) {
				//e.printStackTrace();
				Thread.currentThread().interrupt();
			} finally {
				try {
					ws.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		

		private String toEventType(Kind<?> kind){
			if( kind == null )return null;
			String name = kind.name();
			if( StringUtils.isBlank(name))return null;
			switch(name){
			case "ENTRY_MODIFY":
				return "更新";
			case "ENTRY_DELETE":
				return "删除";
			case "ENTRY_CREATE":
				return "创建";
			}
			return "未知";
		}
	}
	
}
