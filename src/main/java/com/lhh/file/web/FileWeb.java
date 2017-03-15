package com.lhh.file.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhh.file.job.FileJob;
import com.lhh.file.job.enums.FileOnOffEnum;

/**
 * 文件web
 * @createDate 2017年3月13日
 */
@Controller
@RequestMapping("file")
public class FileWeb {
	
	@Resource private FileJob fileJob;
	
	
	/**
	 * 文件开关记录
	 * @param req
	 * @param res
	 * @param type:开关，on=开，off=关
	 */
	@RequestMapping("switchonoff/{type}")
	public void switchOnOff(HttpServletRequest req, HttpServletResponse res,@PathVariable(value="type") String type){
		FileOnOffEnum e = FileOnOffEnum.parser(type);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if( e == null ){
			resultMap.put("msg", "未提供开关正确标识");
			resultMap.put("success", false);
			writeStr2Res(resultMap.toString(),res);
			return;
		}
		
		fileJob.startOrStop(e);
		resultMap.put("success", true);
		resultMap.put("msg", "操作成功");
		writeStr2Res(resultMap.toString(), res);
	}
	
	

    private void writeStr2Res(String jsonStr, HttpServletResponse res) {
        res.setContentType("text/html");
        res.setContentType("text/html; charset=UTF-8");
        try {
            res.getWriter().write(jsonStr);  
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
}
