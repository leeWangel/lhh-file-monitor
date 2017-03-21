package com.lhh.file.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 节点控制
 * @createDate 2017年3月20日
 */
@Controller
@RequestMapping("node")
public class NodeCtrl {

	/**
	 * 初始化节点信息
	 * @param req
	 * @param res
	 * @param type:开关，on=开，off=关
	 */
	@RequestMapping("init")
	public ModelAndView switchOnOff(HttpServletRequest req, HttpServletResponse res){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		JSONArray array = new JSONArray();
		//数据库读取数据做响应的处理
		array.add(testData1());
		array.add(testData2());
		
		resultMap.put("nodeList", array);
		
		resultMap.put("success", true);
		resultMap.put("msg", "操作成功");
		return getModelAndView("", "main", resultMap);
	}
    
    private ModelAndView getModelAndView(String subPath, String fileName, Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<String, Object>(1);
        }
        map.put("subPath", subPath);
        map.put("fileName", fileName);
        return new ModelAndView(subPath + "/" + fileName, map);
    }
    
	
	/**
	 * 测试数据
	 * @return
	 */
	private JSONObject testData1(){
		JSONObject object = new JSONObject();
		object.put("text", "父节点1");
		object.put("selectable", "false");//是否默认展开
		

		JSONArray array = new JSONArray();
		
		JSONObject object1 = new JSONObject();
		object1.put("text", "消息1");
		object1.put("url", "xxxx");
		array.add(object1);
		
		
		JSONObject object2 = new JSONObject();
		object2.put("text", "消息2");
		object2.put("url", "xxxx");
		array.add(object2);
		
		object.put("nodes", array.toString());
		
		return object;
	}
	
	/**
	 * 测试数据
	 * @return
	 */
	private JSONObject testData2(){
		JSONObject object = new JSONObject();
		object.put("text", "父节点2");
		object.put("selectable", "true");//是否默认展开
		

		JSONArray array = new JSONArray();
		
		JSONObject object1 = new JSONObject();
		object1.put("text", "消息1");
		object1.put("url", "xxxx");
		array.add(object1);
		
		
		JSONObject object2 = new JSONObject();
		object2.put("text", "消息2");
		object2.put("url", "xxxx");
		array.add(object2);
		
		object.put("nodes", array.toString());
		
		return object;
	}
	
	
	
	class NodelTitle{
		String text;
		boolean selectable = false;//是否默认展开
		List<NodeModel> nodes = new ArrayList<NodeCtrl.NodeModel>();
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public boolean isSelectable() {
			return selectable;
		}
		public void setSelectable(boolean selectable) {
			this.selectable = selectable;
		}
		public List<NodeModel> getNodes() {
			return nodes;
		}
		public void setNodes(List<NodeModel> nodes) {
			this.nodes = nodes;
		}
		
	}
	
	
	class NodeModel{
		String text;
		String url;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	}
	
}
