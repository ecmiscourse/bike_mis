package cn.easybike.action;

import cn.easybike.entity.Bike;
import cn.easybike.entity.Station;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DistributeAction extends BaseAction<Station> {
	private JSONObject jsonObject =new JSONObject();
	private JSONArray jsonArray= new JSONArray();
	private static final long serialVersionUID = 1L;
	private String stationSn;
	private String newstationSn;
	private String stationName;//站点名称
	private String selectedbikes;
	
	//分页查询
	public String queryByPage(){
		String hql="select s from Station s";
		JSONArray array=new JSONArray();
		for(Station station:stationService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("stationSn", station.getStationSn());
			jo.put("stationName", station.getStationName());
			jo.put("location", station.getLocation());
			jo.put("bikes", station.getBikes().size());
			System.out.println(station.getStationSn());
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", stationService.countByHql("select count(s) from Station s"));
		return "jsonObject";
	}
	//查询除本身以外的所有车辆
	public String queryAllStationExceptionself(){
		String hql="select s from Station s where s.stationSn <> "+stationSn;
		JSONArray array=new JSONArray();
		for(Station station:stationService.queryByPage(hql, 1, 100)){
			if(station.getBikes().size()>0){
				JSONObject jo=new JSONObject();
				jo.put("stationSn", station.getStationSn());
				jo.put("stationName", station.getStationName());
				jsonArray.add(jo);
			}
			
		}
		return "jsonArray";
	}
	//查询每个站点对应的车辆
	public  String queryAll(){
		System.out.println(stationSn);
		String hql="select b from Bike b where b.station.stationSn= "+stationSn ;
		JSONArray array=new JSONArray();
		for(Bike bike:bikeService.queryByPage(hql, 1, 100)){
			JSONObject jo=new JSONObject();
			jo.put("bikeSn", bike.getBikeSn());
			System.out.println("llllldddd12345865");
			jsonArray.add(jo);
		}
		return "jsonArray";
	}
	
	//调入车辆
	public String distribution(){
		jsonObject.put("status", "ok");
		if(selectedbikes!=null&&selectedbikes.length()>0){
			String[] selectedbike=selectedbikes.split(", ");
			if(selectedbike!=null && selectedbike.length>0){
				for(int i=0;i<selectedbikes.length();i++){
					System.out.println(selectedbike[i]);	
					Bike b=bikeService.getByBikeSn(selectedbike[i]);
					if(b!=null){
						Station station=stationService.getByStationSn(newstationSn);
						b.setStation(station);
						bikeService.save(b);
					}				
				}
				
			}
			jsonObject.put("status", "nok");
		}	
		return "jsonObject";		
	}
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public String getSelectedbikes() {
		return selectedbikes;
	}
	public void setSelectedbikes(String selectedbikes) {
		this.selectedbikes = selectedbikes;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public String getStationSn() {
		return stationSn;
	}
	public void setStationSn(String stationSn) {
		this.stationSn = stationSn;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	public String getNewstationSn() {
		return newstationSn;
	}
	public void setNewstationSn(String newstationSn) {
		this.newstationSn = newstationSn;
	}
	
}