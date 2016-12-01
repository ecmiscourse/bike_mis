package cn.easybike.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="maintenance")
public class Maintenance implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String maintenanceSn;//自动生成编号
	private Bike bike;
	private Person reporter;//报修人
	private Timestamp reportDateTime; //报修时间 
	private String reportMark;//报修说明
	private Person repairMan;//修理负责人
	private String repairMark;//维修说明
	private Timestamp repairDateTime; //维修时间 
	private Boolean isRepairable;//是否可维修（是：维修完成，否：车辆报废）
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="maintenance_sn",unique=true,nullable=false,length=100)
	public String getMaintenanceSn() {
		return maintenanceSn;
	}
	public void setMaintenanceSn(String maintenanceSn) {
		this.maintenanceSn = maintenanceSn;
	}
	
	@ManyToOne(targetEntity=Bike.class,cascade=CascadeType.ALL)
	@JoinColumn(name="bike_sn",referencedColumnName="bike_sn")
	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	@ManyToOne(targetEntity=Person.class,cascade=CascadeType.ALL)
	@JoinColumn(name="reporter_sn",referencedColumnName="person_sn")
	public Person getReporter() {
		return reporter;
	}
	public void setReporter(Person reporter) {
		this.reporter = reporter;
	}
	
	@Column(name="report_datetime")
	public Timestamp getReportDateTime() {
		return reportDateTime;
	}
	public void setReportDateTime(Timestamp reportDateTime) {
		this.reportDateTime = reportDateTime;
	}
	
	@Column(name="report_mark",length=200)
	public String getReportMark() {
		return reportMark;
	}
	public void setReportMark(String reportMark) {
		this.reportMark = reportMark;
	}
	
	@ManyToOne(targetEntity=Person.class,cascade=CascadeType.ALL)
	@JoinColumn(name="repairman_sn",referencedColumnName="person_sn")
	public Person getRepairMan() {
		return repairMan;
	}
	public void setRepairMan(Person repairMan) {
		this.repairMan = repairMan;
	}
	
	@Column(name="repair_mark",length=200)
	public String getRepairMark() {
		return repairMark;
	}
	public void setRepairMark(String repairMark) {
		this.repairMark = repairMark;
	}
	
	@Column(name="repair_datetime")
	public Timestamp getRepairDateTime() {
		return repairDateTime;
	}
	public void setRepairDateTime(Timestamp repairDateTime) {
		this.repairDateTime = repairDateTime;
	}
	
	@Column(name="is_repairable")
	public Boolean getIsRepairable() {
		return isRepairable;
	}
	public void setIsRepairable(Boolean isRepairable) {
		this.isRepairable = isRepairable;
	}
}
