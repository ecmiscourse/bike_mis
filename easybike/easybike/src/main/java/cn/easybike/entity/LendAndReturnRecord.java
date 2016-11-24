package cn.easybike.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lend_and_return_record")
public class LendAndReturnRecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String recordSn;//借还记录编号
	private Bike bike;
	private String studentId;//借车人学号
	private String studentName;//借车人姓名
	private String phoneNumber;//借车人联系方式
	private Timestamp lendDateTime; //借出时间
	private Timestamp returnDateTime;//归还时间
	private Boolean isHasReturned;//是否归还
	private String returnMark;//还车备注
	private Person lendPerson;//借出人
	private Person returnPerson;//操作归还的人
	private Station lendStation;//借出站点
	private Station returnStation;//归还站点
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="record_sn",unique=true,nullable=false,length=100)
	public String getRecordSn() {
		return recordSn;
	}
	public void setRecordSn(String recordSn) {
		this.recordSn = recordSn;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="bike_sn",referencedColumnName="bike_sn")
	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	@Column(name="student_id",nullable=false,length=8)
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Column(name="student_name",nullable=false,length=40)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Column(name="phone_number",nullable=false,length=11)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="lend_datetime")
	public Timestamp getLendDateTime() {
		return lendDateTime;
	}
	public void setLendDateTime(Timestamp lendDateTime) {
		this.lendDateTime = lendDateTime;
	}
	
	@Column(name="return_datetime")
	public Timestamp getReturnDateTime() {
		return returnDateTime;
	}
	public void setReturnDateTime(Timestamp returnDateTime) {
		this.returnDateTime = returnDateTime;
	}
	
	@Column(name="is_has_returned")
	public Boolean getIsHasReturned() {
		return isHasReturned;
	}
	public void setIsHasReturned(Boolean isHasReturned) {
		this.isHasReturned = isHasReturned;
	}
	
	@Column(name="return_mark",length=255)
	public String getReturnMark() {
		return returnMark;
	}
	public void setReturnMark(String returnMark) {
		this.returnMark = returnMark;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="lend_person_sn",referencedColumnName="person_sn")
	public Person getLendPerson() {
		return lendPerson;
	}
	public void setLendPerson(Person lendPerson) {
		this.lendPerson = lendPerson;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="return_person_sn",referencedColumnName="person_sn")
	public Person getReturnPerson() {
		return returnPerson;
	}
	public void setReturnPerson(Person returnPerson) {
		this.returnPerson = returnPerson;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="lend_station_sn",referencedColumnName="station_sn")
	public Station getLendStation() {
		return lendStation;
	}
	public void setLendStation(Station lendStation) {
		this.lendStation = lendStation;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="return_station_sn",referencedColumnName="station_sn")
	public Station getReturnStation() {
		return returnStation;
	}
	public void setReturnStation(Station returnStation) {
		this.returnStation = returnStation;
	}
	
	
	
}
