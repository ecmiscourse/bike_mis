package cn.easybike.action;

import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;

/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��17������9:36:07 ��� �½�
*/
public class ResourceAction extends BaseAction<Resource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private JSONArray jsonArray=new JSONArray();
	

	public String getMenu(){
		jsonArray=resourceService.getMenu("", id);
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
