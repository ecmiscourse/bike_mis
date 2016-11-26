package cn.easybike.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name="role",uniqueConstraints = { @UniqueConstraint(columnNames = "role_sn")})
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String roleSn;//角色编号
	private String roleName;//名称
	private Set<Person> persons=new HashSet<Person>(0);
	private Set<Resource> resources=new HashSet<Resource>(0);
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="role_sn",unique=true,length=20)
	public String getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(String roleSn) {
		this.roleSn = roleSn;
	}
	
	@Column(name="role_name",length=40)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Person> getPersons() {
		return persons;
	}
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource", joinColumns = {
			@JoinColumn(name = "role_sn", referencedColumnName="role_sn",nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "resource_sn", referencedColumnName="resource_sn",nullable = false, updatable = false) })
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}
