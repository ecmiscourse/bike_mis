package cn.easybike.action;

import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:50:38 马辉 新建
*/
public class ResourceAction extends BaseAction<Resource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private JSONArray jsonArray=new JSONArray();
	public String getMenu(){
		jsonArray=resourceService.getMenu((String) session.get("personSn"), id);
		return "jsonArray";
	}
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

}
