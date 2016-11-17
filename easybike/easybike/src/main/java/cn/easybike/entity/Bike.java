package cn.easybike.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Date startDate;//投入使用日期
	private Byte status;//状态（0：可借，1：借出，2：报废）
	
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
	
	@Column(name="start_date",length = 19)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="status",nullable=false,length=1)
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	
	

}
