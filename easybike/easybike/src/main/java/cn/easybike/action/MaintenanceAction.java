package cn.easybike.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.UUID;



import com.opensymphony.xwork2.ActionContext;

import cn.easybike.entity.Maintenance;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class MaintenanceAction extends BaseAction<Maintenance> {
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
				jo.put("reporterSn", maintenance.getReporter().getPersonSn());
				jo.put("reportDatetime", maintenance.getReportDateTime().toString());
				jo.put("reportMark", maintenance.getReportMark());
				if(maintenance.getRepairMan()!=null){
					jo.put("repairmanSn", maintenance.getRepairMan().getPersonSn());
				}else{
					jo.put("repairmanSn","");
				}
				if(maintenance.getRepairDateTime()!=null){
					jo.put("repairDatetime",maintenance.getRepairDateTime().toString() );
				}else{
					jo.put("repairDatetime", "");
				}
				jo.put("repairMark",maintenance.getRepairMark());				
				if(maintenance.getIsRepairable()==null || maintenance.getIsRepairable().equals(1)){
					jo.put("isrepairable", "正常");
				}else{
					jo.put("isrepairable", "报废");
				}
				array.add(jo);
			}
			jsonObject.put("total", maintenanceService.countByHql("select count(m) from Maintenance m"));
			jsonObject.put("rows", array);
			System.out.println(jsonObject);

			return "jsonObject";
		}
		//添加维修信息
		public  String operate(){
			Maintenance m=maintenanceService.getByMaintenanceSn(maintenanceSn);			
				System.out.println(repairDatetime);
				System.out.println(repairMark);
				m.setRepairDateTime(repairDatetime);
				m.setRepairMark(repairMark);
				String repairSn=(String) ActionContext.getContext().getSession().get("personSn");
				m.setRepairMan(personService.getByPersonSn(repairSn));
				m.getRepairMan().setPersonSn(repairSn);
				if( isrepairable.equals(1)){
					m.setIsRepairable(true);
				}else{
					m.setIsRepairable(false);
				}	
				maintenanceService.save(m);
			return ids;
	
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
		//车辆保修页面获取数据
		public String queryReportMessage(){
			String hql="select m from Maintenance m";
			JSONArray array=new JSONArray();
			for(Maintenance maintenance:maintenanceService.queryByPage(hql, page,rows)){
				JSONObject jo=new JSONObject();
				jo.put("maintenanceSn",maintenance.getMaintenanceSn());
				jo.put("bikeSn", maintenance.getBike().getBikeSn());
				jo.put("reporterSn", maintenance.getReporter().getPersonSn());
				jo.put("reportDatetime", maintenance.getReportDateTime().toString());
				jo.put("reportMark", maintenance.getReportMark());
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
			try {
				String maintenanceSn=getUUID();
				maintenance.setMaintenanceSn(maintenanceSn);
				maintenance.setBike(bikeService.getByBikeSn(bikeSn));
				maintenance.setReporter(personService.getByPersonSn((String) session.get("personSn")));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				maintenance.setReportDateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
				maintenance.setReportMark(reportMark);
				maintenance.setIsRepairable(false);
				maintenanceService.save(maintenance);
			
			} catch (Exception e) {
				jsonObject.put("status", "nook");
			}
			return "jsonObject";
		}
		public static String getUUID() {
			String s = UUID.randomUUID().toString();
			// 去掉“-”符号
			// return
			// s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
			return s.substring(0, 8);
		}
		
		
		
		
		
		
		
		
		
		
}
