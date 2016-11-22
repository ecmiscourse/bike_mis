package cn.easybike.action;

import cn.easybike.entity.LendAndReturnRecord;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* action<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:33:48 马辉 新建
*/
public class LendAndReturnRecordAction extends BaseAction<LendAndReturnRecord> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSONObject jsonObject=new JSONObject();
	
	private String personSn;
	public String queryByPersonSn(){
		String sn=(String) session.get("personSn");
		String hql1="select p from LendAndReturnRecord p where p.lendPerson.personSn='"+sn+"'";
		String hql2="select count(p) from LendAndReturnRecord p where p.lendPerson.personSn='"+sn+"'";
		JSONArray array=new JSONArray();
		for(LendAndReturnRecord lendAndReturnRecord:lendAndReturnRecordService.queryByPage(hql1, 1, 10)){
			JSONObject jo=new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("bikeSn", lendAndReturnRecord.getBike().getBikeSn());
			jo.put("studentId", lendAndReturnRecord.getStudentId());
			jo.put("studentName", lendAndReturnRecord.getStudentName());
			jo.put("phoneNumber", lendAndReturnRecord.getPhoneNumber());
			jo.put("lendDateTime", lendAndReturnRecord.getReturnDateTime().toString());
			jo.put("isHasReturned", lendAndReturnRecord.getIsHasReturned());
			jo.put("returnPerson", lendAndReturnRecord.getReturnPerson().getPersonName());
			jo.put("returnDateTime", lendAndReturnRecord.getReturnDateTime().toString());
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", lendAndReturnRecordService.countByHql(hql2));
		return "jsonObject";
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public String getPersonSn() {
		return personSn;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public void setPersonSn(String personSn) {
		this.personSn = personSn;
	}
}
