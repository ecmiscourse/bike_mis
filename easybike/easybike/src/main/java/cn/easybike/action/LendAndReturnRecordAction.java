package cn.easybike.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import cn.easybike.entity.Bike;
import cn.easybike.entity.LendAndReturnRecord;
import cn.easybike.entity.Person;
import cn.easybike.entity.Station;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * action<br>
 * 提供对表现层的接口.
 * 
 * @author 马辉
 * @since JDK1.8
 * @history 2016年11月21日下午5:33:48 马辉 新建
 */
public class LendAndReturnRecordAction extends BaseAction<LendAndReturnRecord> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private JSONObject jsonObject = new JSONObject();
	private String recordSn;
	private String  lendStationSn;
	private String bikeSn;
	private String studentId;
	private String phoneNumber;
	private String studentName;
	private String lendPersonSn;
	private String  returnPersonSn;
	private Timestamp lendDateTime; // 借出时间
	private Timestamp returnDateTime;// 归还时间
	private Boolean isHasReturned;// 是否归还
	private String personSn;

	public String getLendStationSn() {
		return lendStationSn;
	}

	public void setLendStationSn(String lendStationSn) {
		this.lendStationSn = lendStationSn;
	}

	public String getBikeSn() {
		return bikeSn;
	}

	public void setBikeSn(String bikeSn) {
		this.bikeSn = bikeSn;
	}

	public String getLendPersonSn() {
		return lendPersonSn;
	}

	public void setLendPersonSn(String lendPersonSn) {
		this.lendPersonSn = lendPersonSn;
	}

	public String getReturnPersonSn() {
		return returnPersonSn;
	}

	public void setReturnPersonSn(String returnPersonSn) {
		this.returnPersonSn = returnPersonSn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getRecordSn() {
		return recordSn;
	}

	public void setRecordSn(String recordSn) {
		this.recordSn = recordSn;
	}
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Timestamp getLendDateTime() {

		return lendDateTime;
	}

	public void setLendDateTime(Timestamp lendDateTime) {
		this.lendDateTime = lendDateTime;
	}

	public Timestamp getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(Timestamp returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public Boolean getIsHasReturned() {
		return isHasReturned;
	}

	public void setIsHasReturned(Boolean isHasReturned) {
		this.isHasReturned = isHasReturned;
	}

	public String queryByPersonSn() {
		String sn = (String) session.get("personSn");
		String hql1 = "select p from LendAndReturnRecord p where p.lendPerson.personSn='" + sn + "'";
		String hql2 = "select count(p) from LendAndReturnRecord p where p.lendPerson.personSn='" + sn + "'";
		JSONArray array = new JSONArray();
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql1, 1, 10)) {
			JSONObject jo = new JSONObject();
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
	 public static String getUUID(){ 
	        String s = UUID.randomUUID().toString(); 
	        //去掉“-”符号 
	        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	    } 
	// 借车页面的分页查询
	public String queryByPage() {
		String hql = "select l from LendAndReturnRecord l";
		JSONArray array = new JSONArray();
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql, page, rows)) {
			JSONObject jo = new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("lendStation", lendAndReturnRecord.getLendStation().getStationSn());
			jo.put("bike", lendAndReturnRecord.getBike().getBikeSn());
			jo.put("studentId", lendAndReturnRecord.getStudentId());
			jo.put("studentName", lendAndReturnRecord.getStudentName());
			jo.put("phoneNumber", lendAndReturnRecord.getPhoneNumber());
			Timestamp timestamp = lendAndReturnRecord.getLendDateTime();
			if (timestamp != null) {
				String timestamp2 = timestamp.toString();
				jo.put("lendDateTime", timestamp2);
			} else {
				jo.put("lendDateTime", "");
			}
			jo.put("isHasReturned", lendAndReturnRecord.getIsHasReturned());
			jo.put("lendPerson", lendAndReturnRecord.getLendPerson().getPersonSn() );
			array.add(jo);
		}
		jsonObject.put("total", lendAndReturnRecordService.countByHql("select count(l) from LendAndReturnRecord l"));
		jsonObject.put("rows", array);
		return "jsonObject";
	}

	public String queryByPage2() { 
		String hql = "select l from LendAndReturnRecord l";
		JSONArray array = new JSONArray();
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql, page, rows)) {
			JSONObject jo = new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("lendStation", lendAndReturnRecord.getLendStation().getStationSn());
			jo.put("bike", lendAndReturnRecord.getBike().getBikeSn());
			jo.put("studentId", lendAndReturnRecord.getStudentId());
			jo.put("studentName", lendAndReturnRecord.getStudentName());
			jo.put("phoneNumber", lendAndReturnRecord.getPhoneNumber());
			Timestamp timestamp = lendAndReturnRecord.getLendDateTime();
			if (timestamp != null) {
				String timestamp2 = timestamp.toString();
				jo.put("lendDateTime", timestamp2);
			} else {
				jo.put("lendDateTime", "");
			}
			jo.put("isHasReturned", lendAndReturnRecord.getIsHasReturned());
			jo.put("lendPerson", lendAndReturnRecord.getLendPerson().getPersonSn() );
			array.add(jo);
		}
		jsonObject.put("total", lendAndReturnRecordService.countByHql("select count(l) from LendAndReturnRecord l"));
		jsonObject.put("rows", array);
		return "jsonObject";
	}

	public String delete() {
		jsonObject.put("status", "ok");
		try {
			lendAndReturnRecordService.deleteByRecordSn(recordSn);
		} catch (Exception e) {
			jsonObject.put("status", "nook");
		}
		System.out.println(id);
		return "jsonObject";
	}

	public String save() {
       jsonObject.put("status", "ok");
       LendAndReturnRecord lendAndReturnRecord = new LendAndReturnRecord();
       try{
    	   lendAndReturnRecord.setStudentName(studentName);
    	   lendAndReturnRecord.setStudentId(studentId);
    	   lendAndReturnRecord.setPhoneNumber(phoneNumber);
    	 //  lendAndReturnRecord.setLendStation(lendStationSn);
    	  // lendAndReturnRecord.setLendPerson(lendPersonSn);
    	   
    	   String recordSn=getUUID();
    	   System.out.println(recordSn);
    	   lendAndReturnRecord.setRecordSn(recordSn);
    	   lendAndReturnRecord.setIsHasReturned(false);
    	   lendAndReturnRecord.setLendPerson(personService.getByPersonSn((String) session.get("personSn")));
    	   // no time
    	   lendAndReturnRecordService.save(lendAndReturnRecord);
    	   
    	   
       }catch(Exception e){
    	   jsonObject.put("status", "nook");
       }
		return "jsonObject";
	}

	public String add2() {

		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
