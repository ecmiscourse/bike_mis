package cn.easybike.action;


import cn.easybike.action.BaseAction;
import cn.easybike.entity.Person;
import cn.easybike.entity.Resource;
import cn.easybike.entity.Role;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月28日 下午1:20:32 马辉 新建
*/
public class RoleAction extends BaseAction<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject jsonObject=new JSONObject();
	private String roleSn;
	private String roleName;
	private String oldRoleSn;
	private String personSn;
	private String resourceSn;
	
	
	//角色授权
	public String addResource(){
		jsonObject.put("status", "ok");
		try{
			Role role = roleService.getBySn(roleSn);
			Resource resource=resourceService.getBySn(resourceSn);
			if(role!=null&&resource!=null){
				role.getResources().add(resource);
				roleService.update(role);
			}else{
				jsonObject.put("status", "nook");
			}
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//角色撤权
	public String removeResource(){
		jsonObject.put("status", "ok");
		try{
			Role role = roleService.getBySn(roleSn);
			Resource resource=resourceService.getBySn(resourceSn);
			if(role!=null&&resource!=null){
				role.getResources().remove(resource);
				roleService.update(role);
			}else{
				jsonObject.put("status", "nook");
			}
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//人员授权
	public String addPerson(){
		jsonObject.put("status", "ok");
		try{
			Role role = roleService.getBySn(roleSn);
			Person person=personService.getByPersonSn(personSn);
			if(role!=null&&person!=null){
				person.getRoles().add(role);
				personService.update(person);
			}else{
				jsonObject.put("status", "nook");
			}
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//角色删除
	public String delete(){
		jsonObject.put("status", "ok");
		try{
			roleService.deleteBySn(roleSn);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//人员撤权
	public String removePerson(){
		jsonObject.put("status", "ok");
		try{
			Role role = roleService.getBySn(roleSn);
			Person person=personService.getByPersonSn(personSn);
			if(role!=null&&person!=null){
				person.getRoles().remove(role);
				personService.update(person);
			}else{
				jsonObject.put("status", "nook");
			}
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//查询角色
	public String queryByPage(){
		JSONArray array=new JSONArray();
		String hql="select r from Role r";
		for(Role role:roleService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("roleSn", role.getRoleSn());
			jo.put("roleName", role.getRoleName());
			jo.put("persons", role.getPersons().size());
			array.add(jo);
		}
		jsonObject.put("total", roleService.countAll());
		jsonObject.put("rows", array);
		return "jsonObject";
	}
	
	//添加
	public String save(){
		jsonObject.put("status", "ok");
		Role role=new Role();
		try{
			role.setRoleSn(roleSn);
			role.setRoleName(roleName);
			roleService.save(role);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//更新
	public String update(){
		jsonObject.put("status", "ok");
		try{
			Role role = roleService.getBySn(oldRoleSn);
			role.setRoleSn(roleSn);
			role.setRoleName(roleName);
			roleService.update(role);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//验证角色是否存在
	public String isExist(){
		if(roleService.getBySn(roleSn)!=null){
			jsonObject.put("isExist", true);
		}else{
			jsonObject.put("isExist", false);
		}
		return "jsonObject";
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public String getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(String roleSn) {
		this.roleSn = roleSn;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getOldRoleSn() {
		return oldRoleSn;
	}
	public void setOldRoleSn(String oldRoleSn) {
		this.oldRoleSn = oldRoleSn;
	}
	public String getPersonSn() {
		return personSn;
	}
	public void setPersonSn(String personSn) {
		this.personSn = personSn;
	}
	public String getResourceSn() {
		return resourceSn;
	}
	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}
}
