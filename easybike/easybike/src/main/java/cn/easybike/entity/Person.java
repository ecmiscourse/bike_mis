package cn.easybike.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
@Entity
@Table(name="person",uniqueConstraints = { @UniqueConstraint(columnNames = "person_sn")})
public class Person implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String personSn;//学号
	private String personName;//姓名
	private String password;//密码
	private Byte sex;//性别
	private String cellphoneNumber;//手机号
	private Set<Role> roles=new HashSet<Role>();
	
	@Id
	@GeneratedValue(strategy=IDENTITY)	
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="person_sn",unique=true,length=8)
	public String getPersonSn() {
		return personSn;
	}
	public void setPersonSn(String personSn) {
		this.personSn = personSn;
	}
	
	@Column(name="person_name",nullable=false,length=40)
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@Column(name="pasword",nullable=false,length=32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "sex",length=1)
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	
	@Column(name = "mobile_number", length = 11)
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "person_role", joinColumns = {
			@JoinColumn(name = "person_sn", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role_sn", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
