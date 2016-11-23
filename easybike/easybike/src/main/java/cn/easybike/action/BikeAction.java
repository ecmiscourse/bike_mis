package cn.easybike.action;

import java.time.LocalDate;

import cn.easybike.entity.Bike;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:26:42 马辉 新建
*/
public class BikeAction extends BaseAction<Bike> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stationSn;
	private JSONObject jsonObject=new JSONObject();
	private String bikeSn;
	private String startDate;
	private String oldBikeSn;
	
	
	//分页查询
	public String queryByPage(){
		String hql="select b from Bike b";
		JSONArray array=new JSONArray();
		for(Bike bike:bikeService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("bikeSn", bike.getBikeSn());
			jo.put("startDate", bike.getStartDate().toString());
			jo.put("status", bike.getStatus());
			if(bike.getStation()!=null){
				jo.put("station", bike.getStation().getStationName());
			}
			if(bike.getEndDate()!=null){
				jo.put("endDate", bike.getEndDate().toString());
			}
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", bikeService.countByHql(hql.replaceFirst("b", "count(b)")));
		return "jsonObject";
	}
	//添加
	public String save(){
		jsonObject.put("status", "ok");
		Bike bike=new Bike();
		try{
			bike.setBikeSn(bikeSn);
			bike.setStartDate(LocalDate.parse(startDate));
			bike.setStation(stationService.getByStationSn("0001"));
			bike.setStatus((byte) 0);
			bikeService.save(bike);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//更新
	public String update(){
		jsonObject.put("status", "ok");
		try{
			Bike bike = bikeService.getByBikeSn(oldBikeSn);
			bike.setBikeSn(bikeSn);
			bike.setStartDate(LocalDate.parse(startDate));
			bikeService.update(bike);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		
		return "jsonObject";
	}
	
	//删除
	public String delete(){
		jsonObject.put("status", "ok");
		try{
			bikeService.deleteBySn(bikeSn);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//验证编号是否存在
	public String isExist(){
		if(bikeService.getByBikeSn(bikeSn)!=null){
			jsonObject.put("isExist", true);
		}else{
			jsonObject.put("isExist", false);
		}
		return "jsonObject";
	}
	//根据站点得到车辆
	public String getByStationSn(){
		String hql1="select b from Bike b where b.station.stationSn='"+stationSn+"'";
		String hql2="select count(b) from Bike b where b.station.stationSn='"+stationSn+"'";
		JSONArray array=new JSONArray();
		for(Bike bike:bikeService.queryByPage(hql1, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("bikeSn", bike.getBikeSn());
			jo.put("startDate", bike.getStartDate().toString());
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", bikeService.countByHql(hql2));
		return "jsonObject";
	}
	public String getStationSn() {
		return stationSn;
	}
	public void setStationSn(String stationSn) {
		this.stationSn = stationSn;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public String getBikeSn() {
		return bikeSn;
	}
	public void setBikeSn(String bikeSn) {
		this.bikeSn = bikeSn;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getOldBikeSn() {
		return oldBikeSn;
	}
	public void setOldBikeSn(String oldBikeSn) {
		this.oldBikeSn = oldBikeSn;
	}
}
