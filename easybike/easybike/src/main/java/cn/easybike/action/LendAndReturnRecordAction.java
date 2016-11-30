package cn.easybike.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.UUID;

import cn.easybike.entity.Bike;
import cn.easybike.entity.LendAndReturnRecord;
import cn.easybike.entity.Maintenance;
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
	private JSONArray jsonArray = new JSONArray();
	private String recordSn;
	private String stationSn;
	private String lendStation;
	private String lendStationSn;
	private String lendStationName;
	private String bikeSn;
	private String bike;
	private String studentId;
	private String phoneNumber;
	private String studentName;
	private String lendPersonSn;
	private String returnPersonSn;
	private String returnStationSn;
	private String returnStationName;
	private Timestamp lendDateTime; // 借出时间
	private Timestamp returnDateTime;// 归还时间
	private Boolean isHasReturned;// 是否归还
	private String personSn;
	private String oldStudentId;
	private String returnMark;

	public String getReturnMark() {
		return returnMark;
	}

	public void setReturnMark(String returnMark) {
		this.returnMark = returnMark;
	}

	public String getLendStationName() {
		return lendStationName;
	}

	public void setLendStationName(String lendStationName) {
		this.lendStationName = lendStationName;
	}

	public String getReturnStationName() {
		return returnStationName;
	}

	public void setReturnStationName(String returnStationName) {
		this.returnStationName = returnStationName;
	}

	public String getLendStation() {
		return lendStation;
	}

	public void setLendStation(String lendStation) {
		this.lendStation = lendStation;
	}

	public String getBike() {
		return bike;
	}

	public void setBike(String bike) {
		this.bike = bike;
	}

	public String getReturnStationSn() {
		return returnStationSn;
	}

	public void setReturnStationSn(String returnStationSn) {
		this.returnStationSn = returnStationSn;
	}

	public String getOldStudentId() {
		return oldStudentId;
	}

	public void setOldStudentId(String oldStudentId) {
		this.oldStudentId = oldStudentId;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getStationSn() {
		return stationSn;
	}

	public void setStationSn(String stationSn) {
		this.stationSn = stationSn;
	}

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
		String hql1 = "";
		String hql2 = "";
		if (bikeSn == null || bikeSn == "") {
			hql1 = "select p from LendAndReturnRecord p where p.lendPerson.personSn='" + sn + "'";
			hql2 = "select count(p) from LendAndReturnRecord p where p.lendPerson.personSn='" + sn + "'";
		} else {
			hql1 = "select p from LendAndReturnRecord p where p.lendPerson.personSn='" + sn + "' and p.bike.bikeSn= '"
					+ bikeSn + "'";
			hql2 = "select count(p) from LendAndReturnRecord p where p.lendPerson.personSn='" + sn
					+ "' and p.bike.bikeSn= '" + bikeSn + "'";
		}
		JSONArray array = new JSONArray();
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql1, page, rows)) {
			JSONObject jo = new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("bikeSn", lendAndReturnRecord.getBike().getBikeSn());
			jo.put("studentId", lendAndReturnRecord.getStudentId());
			jo.put("studentName", lendAndReturnRecord.getStudentName());
			jo.put("phoneNumber", lendAndReturnRecord.getPhoneNumber());
			jo.put("lendPerson", lendAndReturnRecord.getLendPerson().getPersonName());
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

	public String queryByBikeSn() {
		String hql1 = "select p from LendAndReturnRecord p where p.bike.bikeSn='" + bikeSn + "'";
		String hql2 = "select count(p) from LendAndReturnRecord p where p.bike.bikeSn='" + bikeSn + "'";
		JSONArray array = new JSONArray();
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql1, page, rows)) {
			JSONObject jo = new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("bikeSn", lendAndReturnRecord.getBike().getBikeSn());
			jo.put("studentId", lendAndReturnRecord.getStudentId());
			jo.put("studentName", lendAndReturnRecord.getStudentName());
			jo.put("phoneNumber", lendAndReturnRecord.getPhoneNumber());
			jo.put("lendDateTime", lendAndReturnRecord.getReturnDateTime().toString());
			jo.put("lendPerson", lendAndReturnRecord.getLendPerson().getPersonName());
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

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		// return
		// s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
		return s.substring(0, 8);
	}

	// 借车页面的分页查询
	public String queryByPage() {
		String hql = "select l from LendAndReturnRecord l";
		JSONArray array = new JSONArray();
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql, page, rows)) {
			JSONObject jo = new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("lendStationSn", lendAndReturnRecord.getLendStation().getStationSn());
			jo.put("bikeSn", lendAndReturnRecord.getBike().getBikeSn());
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
			jo.put("lendPerson", lendAndReturnRecord.getLendPerson().getPersonSn());
			array.add(jo);
		}
		jsonObject.put("total", lendAndReturnRecordService.countByHql("select count(l) from LendAndReturnRecord l"));
		jsonObject.put("rows", array);
		return "jsonObject";
	}

	// 还车页面
	public String queryByPage2() {
		String hql = "select a from LendAndReturnRecord a";
		// String hql2="select l from LendAndReturnRecord l where
		// l.studentId='"+studentId+"'";
		JSONArray array = new JSONArray();
		if (studentId != null && studentId.trim().length() > 0) {
			hql += " where a.studentId='" + studentId + "'";
		}
		for (LendAndReturnRecord lendAndReturnRecord : lendAndReturnRecordService.queryByPage(hql, page, rows)) {
			JSONObject jo = new JSONObject();
			jo.put("recordSn", lendAndReturnRecord.getRecordSn());
			jo.put("lendStationSn", lendAndReturnRecord.getLendStation().getStationSn());
			jo.put("bikeSn", lendAndReturnRecord.getBike().getBikeSn());
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
			jo.put("lendPerson", lendAndReturnRecord.getLendPerson().getPersonSn());

			if (lendAndReturnRecord.getReturnPerson() != null) {
				jo.put("returnPersonName", lendAndReturnRecord.getReturnPerson().getPersonName());
			}
			Timestamp timestamp3 = lendAndReturnRecord.getReturnDateTime();
			if (timestamp3 != null) {
				String timestamp4 = timestamp3.toString();
				jo.put("returnDateTime", timestamp4);
			} else {
				jo.put("returnDateTime", "");
			}
			if (lendAndReturnRecord.getReturnStation() != null) {
				jo.put("returnStationName", lendAndReturnRecord.getReturnStation().getStationName());
			}
			jo.put("returnMark", lendAndReturnRecord.getReturnMark());
			array.add(jo);
		}
		jsonObject.put("total", lendAndReturnRecordService.countByHql(hql.replaceFirst("a", "count(a)")));
		jsonObject.put("rows", array);
		return "jsonObject";
	}

	// 下拉框获取bike
	public String getAllBike() {
		for (Bike bike : bikeService.queryAll()) {
			JSONObject jo = new JSONObject();
			jo.put("bikeSn", bike.getBikeSn());
			jo.put("id", bike.getId());
			jsonArray.add(jo);
		}
		return "jsonArray";
	}

	// 借车下拉框获取station
	public String getAllStation() {
		for (Station station : stationService.queryAll()) {
			JSONObject jo = new JSONObject();
			jo.put("lendStationSn", station.getStationSn());
			jo.put("stationName", station.getStationName());
			jsonArray.add(jo);
		}
		return "jsonArray";
	}

	// 还车下拉框获取station
	public String getAllStation2() {
		for (Station station : stationService.queryAll()) {
			JSONObject jo = new JSONObject();
			jo.put("returnStationSn", station.getStationSn());
			jo.put("returnStationName", station.getStationName());
			jsonArray.add(jo);
		}
		return "jsonArray";
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
		try {
			lendAndReturnRecord.setStudentName(studentName);
			lendAndReturnRecord.setStudentId(studentId);
			lendAndReturnRecord.setPhoneNumber(phoneNumber);
			lendAndReturnRecord.setLendStation(stationService.getByStationSn(stationSn));
			lendAndReturnRecord.setBike(bikeService.getByBikeSn(bikeSn));

			String recordSn = getUUID();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			lendAndReturnRecord.setLendDateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));

			lendAndReturnRecord.setRecordSn(recordSn);
			lendAndReturnRecord.setIsHasReturned(false);
			lendAndReturnRecord.setLendPerson(personService.getByPersonSn((String) session.get("personSn")));

			lendAndReturnRecordService.save(lendAndReturnRecord);

		} catch (Exception e) {
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}

	

	public String update() {
		jsonObject.put("status", "ok");
		LendAndReturnRecord lendAndReturnRecord = lendAndReturnRecordService.getByStudentId(oldStudentId);
		if (lendAndReturnRecord != null) {
			try {
				lendAndReturnRecord.setStudentId(studentId);
				lendAndReturnRecord.setStudentName(studentName);
				lendAndReturnRecord.setPhoneNumber(phoneNumber);
				lendAndReturnRecord.setLendStation(stationService.getByStationSn(lendStationSn));
				lendAndReturnRecord.setBike(bikeService.getByBikeSn(bikeSn));
				lendAndReturnRecordService.update(lendAndReturnRecord);
			} catch (Exception e) {
				jsonObject.put("status", "nook");
			}
		} else {
			jsonObject.put("status", "took");
		}
		return "jsonObject";
	}

	// 还车页面的录入
	public String update2() {
		jsonObject.put("status", "ok");
		LendAndReturnRecord lendAndReturnRecord = lendAndReturnRecordService.getByRecordSn(recordSn);
		if (lendAndReturnRecord != null) {
			try {
				lendAndReturnRecord.setIsHasReturned(true);
				lendAndReturnRecord.setReturnPerson(personService.getByPersonSn((String) session.get("personSn")));
				lendAndReturnRecord.setReturnStation(stationService.getByStationSn(returnStationSn));
				lendAndReturnRecord.setReturnMark(returnMark);
				// lendAndReturnRecord.setReturnDateTime(returnDateTime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				lendAndReturnRecord.setReturnDateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
				lendAndReturnRecordService.update(lendAndReturnRecord);
			} catch (Exception e) {
				jsonObject.put("status", "nook");
			}
		} else {
			jsonObject.put("status", "took");
		}
		return "jsonObject";
	}

	public String isExist() {
		if (lendAndReturnRecordService.getByStudentId(studentId) != null) {
			jsonObject.put("isExist", true);
		} else {
			jsonObject.put("isExist", false);
		}
		return "jsonObject";
	}

}
