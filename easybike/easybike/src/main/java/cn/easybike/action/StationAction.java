package cn.easybike.action;

import cn.easybike.entity.Station;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* action<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:54:46 马辉 新建
*/
public class StationAction extends BaseAction<Station> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject jsonObject =new JSONObject();
	private String stationSn;
	private String stationName;//站点名称
	private String location;//站点位置
	private String oldStationSn;


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
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", stationService.countByHql("select count(s) from Station s"));
		return "jsonObject";
	}

	//添加站点
	public String save(){
		jsonObject.put("status", "ok");
		Station station=new Station();
		try{
			station.setStationSn(stationSn);
			station.setStationName(stationName);
			station.setLocation(location);
			stationService.save(station);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	
	//修改
	public String update(){
		jsonObject.put("status", "ok");
		Station station=stationService.getByStationSn(oldStationSn);
		try{
			station.setStationSn(stationSn);
			station.setStationName(stationName);
			station.setLocation(location);
			stationService.update(station);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	
	//删除站点
	public String delete(){
		jsonObject.put("status", "ok");
		try{
			stationService.deleteBySn(stationSn);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//验证编号是否存在
	public String isExist(){
		if(stationService.getByStationSn(stationSn)!=null){
			jsonObject.put("isExist", true);
		}else{
			jsonObject.put("isExist", false);
		}
		return "jsonObject";
	}

	public JSONObject getJsonObject() {
		return jsonObject;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getOldStationSn() {
		return oldStationSn;
	}

	public void setOldStationSn(String oldStationSn) {
		this.oldStationSn = oldStationSn;
	}
}
