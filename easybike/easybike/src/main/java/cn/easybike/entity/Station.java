package cn.easybike.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
/**
* 站点实体类<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月19日下午5:30:41 马辉 新建
*/
@Entity
@Table(name="station")
public class Station implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String stationSn;//站点编号
	private String sattionName;//站点名称
	private String location;//站点位置
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="station_sn",unique=true,length=4)
	public String getStationSn() {
		return stationSn;
	}
	public void setStationSn(String stationSn) {
		this.stationSn = stationSn;
	}
	
	@Column(name="station_name",length=30)
	public String getSattionName() {
		return sattionName;
	}
	public void setSattionName(String sattionName) {
		this.sattionName = sattionName;
	}
	
	@Column(name="location",length=255)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	

	
}
