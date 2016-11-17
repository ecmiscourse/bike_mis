package cn.easybike.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="resource",uniqueConstraints = { @UniqueConstraint(columnNames = "resource_sn")})
public class Resource implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Resource parent;//父级
	private String resourceSn;//编号
	private String resourceName;//资源名
	private String resourceType;//资源类型（页面menu，按钮button）
	private String url;//链接
	private int showSequence;//显示循序
	private Set<Resource> children=new HashSet<Resource>(0);
	private Set<Role> roles=new HashSet<Role>(0);
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_resource_sn",referencedColumnName="resource_sn")
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	
	@Column(name="resource_sn",unique=true,nullable=false,length=20)
	public String getResourceSn() {
		return resourceSn;
	}
	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}
	
	@Column(name="resource_name",nullable=false,length=20)
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	@Column(name="resource_type",nullable=false,length=6)
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	@Column(name="url",unique=true,length=100)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="show_sequence",nullable=false)
	public int getShowSequence() {
		return showSequence;
	}
	public void setShowSequence(int showSequence) {
		this.showSequence = showSequence;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="parent")
	public Set<Resource> getChildren() {
		return children;
	}
	public void setChildren(Set<Resource> children) {
		this.children = children;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="resources")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
}
