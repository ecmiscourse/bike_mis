package cn.easybike.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;



import com.opensymphony.xwork2.ActionContext;

import cn.easybike.entity.Bike;
import cn.easybike.entity.Maintenance;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class MaintenanceAction extends BaseAction<Maintenance> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maintenanceSn;
	private String repairMark;
	private String reportMark;
	private Timestamp repairDatetime;
	private String  isrepairable;
	private JSONObject jsonObject=new JSONObject();
	private String bikeSn;
	private String repairmanSn;
	private String repairDateTime;
	//分页查询
	public String queryByPage(){
		String hql="select m from Maintenance m";
		JSONArray array=new JSONArray();
		for(Maintenance maintenance:maintenanceService.queryByPage(hql, page,rows)){
			JSONObject jo=new JSONObject();
			jo.put("maintenanceSn",maintenance.getMaintenanceSn());
			jo.put("bikeSn", maintenance.getBike().getBikeSn());
			jo.put("reporterName", maintenance.getReporter().getPersonName());
			jo.put("reportDatetime", maintenance.getReportDateTime().toString());
			jo.put("reportMark", maintenance.getReportMark());
			if(maintenance.getRepairMan()!=null){
				jo.put("repairmanName", maintenance.getRepairMan().getPersonName());
			}else{
				jo.put("repairmanSn","");
			}
			if(maintenance.getRepairDateTime()!=null){
				jo.put("repairDatetime",maintenance.getRepairDateTime().toString() );
			}else{
				jo.put("repairDatetime", "");
			}
			jo.put("repairMark",maintenance.getRepairMark());				
			jo.put("isrepairable", maintenance.getIsRepairable());
			array.add(jo);
		}
		jsonObject.put("total", maintenanceService.countByHql("select count(m) from Maintenance m"));
		jsonObject.put("rows", array);
		return "jsonObject";
		}
		//添加维修信息
		public  String operate(){
			Maintenance m=maintenanceService.getByMaintenanceSn(maintenanceSn);	
			Bike bike = m.getBike();
			
			m.setRepairDateTime(repairDatetime);
			m.setRepairMark(repairMark);
			String repairSn=(String) ActionContext.getContext().getSession().get("personSn");
			m.setRepairMan(personService.getByPersonSn(repairSn));
			if( isrepairable.equals(1)){
				m.setIsRepairable(true);
				if(bike!=null){
					bike.setStatus((byte)0);
					bike.setStation(stationService.getByStationSn("0001"));
					bikeService.update(bike);
				}
			}else{
				m.setIsRepairable(false);
				if(bike!=null){
					bike.setStatus((byte)3);
					bike.setStation(null);
					bikeService.update(bike);
				}
			}	
			maintenanceService.save(m);
			return ids;
	
		}

		
		//车辆保修页面获取数据
		public String queryReportMessage(){
			String hql="select m from Maintenance m order by m.reportDateTime desc";
			JSONArray array=new JSONArray();
			for(Maintenance maintenance:maintenanceService.queryByPage(hql, page,rows)){
				JSONObject jo=new JSONObject();
				jo.put("maintenanceSn",maintenance.getMaintenanceSn());
				jo.put("bikeSn", maintenance.getBike().getBikeSn());
				jo.put("reporterName", maintenance.getReporter().getPersonName());
				jo.put("reportDatetime", maintenance.getReportDateTime().toString());
				jo.put("reportMark", maintenance.getReportMark());
				jo.put("isrepairable", maintenance.getIsRepairable());
				array.add(jo);
			}
			jsonObject.put("total", maintenanceService.countByHql("select count(m) from Maintenance m"));
			jsonObject.put("rows", array);
			return "jsonObject";
		}
		//保修页面的add
		public String save2() {
			jsonObject.put("status", "ok");
			Maintenance maintenance= new Maintenance();
			Bike bike = bikeService.getByBikeSn(bikeSn);
			try {
				String maintenanceSn=new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
				maintenance.setMaintenanceSn(maintenanceSn);
				maintenance.setBike(bikeService.getByBikeSn(bikeSn));
				maintenance.setReporter(personService.getByPersonSn((String) session.get("personSn")));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				maintenance.setReportDateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
				maintenance.setReportMark(reportMark);
				maintenanceService.save(maintenance);
				bike.setStatus((byte)2);
				bike.setStation(null);
				bikeService.update(bike);								
			} catch (Exception e) {
				jsonObject.put("status", "nook");
			}
			return "jsonObject";
		}
		
		
		public JSONObject getJsonObject() {
			return jsonObject;
		}

		public void setJsonObject(JSONObject jsonObject) {
			this.jsonObject = jsonObject;
		}

		public String getMaintenanceSn() {
			return maintenanceSn;
		}
		public void setMaintenanceSn(String maintenanceSn) {
			this.maintenanceSn = maintenanceSn;
		}	
		public String getRepairMark() {
			return repairMark;
		}
		public void setRepairMark(String repairMark) {
			this.repairMark = repairMark;
		}
	

	
		public String getReportMark() {
			return reportMark;
		}
		public void setReportMark(String reportMark) {
			this.reportMark = reportMark;
		}
		public Timestamp getRepairDatetime() {
			return repairDatetime;
		}
		public void setRepairDatetime(Timestamp repairDatetime) {
			this.repairDatetime = repairDatetime;
		}
		public String getIsrepairable() {
			return isrepairable;
		}
		public void setIsrepairable(String isrepairable) {
			this.isrepairable = isrepairable;
		}
		
		public String getBikeSn() {
			return bikeSn;
		}
		public void setBikeSn(String bikeSn) {
			this.bikeSn = bikeSn;
		}
		public String getRepairmanSn() {
			return repairmanSn;
		}
		public void setRepairmanSn(String repairmanSn) {
			this.repairmanSn = repairmanSn;
		}
		public String getRepairDateTime() {
			return repairDateTime;
		}
		public void setRepairDateTime(String repairDateTime) {
			this.repairDateTime = repairDateTime;
		}
		
		
		
		
		
		
		
}
