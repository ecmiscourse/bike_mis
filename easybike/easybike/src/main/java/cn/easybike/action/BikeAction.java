package cn.easybike.action;

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
	

}
