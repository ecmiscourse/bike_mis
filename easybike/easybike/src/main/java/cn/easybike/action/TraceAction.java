package cn.easybike.action;

import cn.easybike.entity.Bike;
import cn.easybike.entity.LendAndReturnRecord;
import cn.easybike.entity.Station;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TraceAction extends BaseAction<Station> {
	private JSONArray jsonArray= new JSONArray();
	private JSONObject jsonObject =new JSONObject();
	private static final long serialVersionUID = 1L;
	private String bikeSn;
	
	//
	public String queryAllbikes(){
		String hql="select b from Bike b";
		if(bikeSn!=null&&bikeSn.trim().length()>0){
			hql+=" where b.bikeSn like '%"+bikeSn+"%'";
		}
		JSONArray array=new JSONArray();
		for(Bike bike:bikeService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();			
			jo.put("bikeSn",bike.getBikeSn());	
			jo.put("status", bike.getStatus());
			array.add(jo);
		}		
		jsonObject.put("rows", array);
		jsonObject.put("total",bikeService.countByHql(hql.replaceFirst("b", "count(b)")));
		return "jsonObject";
	}
	//根据车辆编号分页查询
	public String queryAllTrace(){
		System.out.println(bikeSn);
		String hql="";
		String hql2="";
	
			hql="select lendandreturnrecord from LendAndReturnRecord lendandreturnrecord  where lendandreturnrecord.bike.bikeSn= "+bikeSn;
			hql2="select count(lendandreturnrecord) from LendAndReturnRecord lendandreturnrecord where lendandreturnrecord.bike.bikeSn= "+bikeSn;
		
		JSONArray array=new JSONArray();
		for(LendAndReturnRecord lendandreturnrecord:lendAndReturnRecordService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("recordSn", lendandreturnrecord.getRecordSn());
			jo.put("studentId", lendandreturnrecord.getStudentName());
			jo.put("lendDateTime",lendandreturnrecord.getLendDateTime().toString());
			jo.put("returnDateTime",lendandreturnrecord.getReturnDateTime().toString());
			if(lendandreturnrecord.getIsHasReturned()==true){
				jo.put("isHasReturned","是" );
			}else{
				jo.put("isHasReturned","否" );
			}
			if( lendandreturnrecord.getLendPerson()!=null){
				jo.put("lendPerson", lendandreturnrecord.getLendPerson().getPersonName());
			}else{
				jo.put("lendPerson", "");
			}	
			if(lendandreturnrecord.getLendStation()!=null){
				System.out.println("qq");
				jo.put("lendStationSn", lendandreturnrecord.getLendStation().getStationName());
			}else{
				jo.put("lendStationSn", "");
			}
			
			if( lendandreturnrecord.getReturnPerson()!=null){
				jo.put("returnPerson", lendandreturnrecord.getReturnPerson().getPersonName());
			}else{
				jo.put("returnPerson", "");
			}	
			if(lendandreturnrecord.getReturnStation()!=null){
				jo.put("returnStationSn", lendandreturnrecord.getReturnStation().getStationName());
			}else{
				jo.put("returnStationSn", "");
			}
		
			
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total",lendAndReturnRecordService.countByHql(hql2));
		
		return "jsonObject";
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getBikeSn() {
		return bikeSn;
	}
	public void setBikeSn(String bikeSn) {
		this.bikeSn = bikeSn;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
}
	