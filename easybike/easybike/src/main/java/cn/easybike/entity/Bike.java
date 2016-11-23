package cn.easybike.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name="bike",uniqueConstraints = { @UniqueConstraint(columnNames = "bike_sn")})
public class Bike implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String bikeSn;
	private LocalDate startDate;//开始使用日期
	private Byte status;//状态 0：可借 ，1：借出，2，维修中，3：报废
	private LocalDate endDate;//报废时间
	private Station station;//所在站点
	
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="bike_sn",unique=true,length=8)
	public String getBikeSn() {
		return bikeSn;
	}
	public void setBikeSn(String bikeSn) {
		this.bikeSn = bikeSn;
	}
	
	@Column(name="start_date")
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	@Column(name="status",nullable=false,length=1)
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	@Column(name="end_date")
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	@ManyToOne(targetEntity=Station.class,cascade=CascadeType.ALL)
	@JoinColumn(name="station_sn",referencedColumnName="station_sn")
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	

}
