package cn.easybike.action;

import java.sql.Timestamp;
import java.util.Date;

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
	private Timestamp repairDatetime;
	private String  isrepairable;
	private JSONObject jsonObject=new JSONObject();

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
				jo.put("reportMark", maintenance.getRepairMark());
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
}
