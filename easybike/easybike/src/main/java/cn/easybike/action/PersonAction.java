package cn.easybike.action;

import org.apache.struts2.ServletActionContext;

import cn.easybike.entity.Person;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:50:23 马辉 新建
*/
public class PersonAction extends BaseAction<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personSn;
	private String password;
	private String oldPersonSn;
	private JSONObject jsonObject=new JSONObject();
	private Byte sex;
	private String cellphoneNumber;
	private String personName;
	
	
	//分页查询
	public String queryByPage(){
		String hql="select p from Person p";
		JSONArray array=new JSONArray();
		for(Person person:personService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("personSn", person.getPersonSn());
			jo.put("personName", person.getPersonName());
			jo.put("sex", person.getSex());
			jo.put("cellphoneNumber", person.getCellphoneNumber());
			array.add(jo);
		}
		jsonObject.put("total", personService.countByHql("select count(p) from Person p"));
		jsonObject.put("rows",array);
		return "jsonObject";
	}
	
	//添加人员
	public String save(){
		jsonObject.put("status", "ok");
		Person person=new Person();
		try{
			person.setPersonSn(personSn);
			person.setCellphoneNumber(cellphoneNumber);
			person.setPersonName(personName);
			person.setPassword("123456");
			person.setSex(sex);
			personService.save(person);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//修改人员信息
	public String update(){
		jsonObject.put("status", "ok");
		Person person=personService.getByPersonSn(oldPersonSn);
		if(person!=null){
			try{
				person.setPersonSn(personSn);
				person.setCellphoneNumber(cellphoneNumber);
				person.setPersonName(personName);
				person.setSex(sex);
				personService.update(person);
			}catch(Exception e){
				jsonObject.put("status", "nook");
			}
		}else{
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//删除人员
	public String delete(){
		jsonObject.put("status", "ok");
		try{
			personService.deleteBySn(personSn);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//验证学号是否存在
	public String isExist(){
		if(personService.getByPersonSn(personSn)!=null){
			jsonObject.put("isExist", true);
		}else{
			jsonObject.put("isExist", false);
		}
		return "jsonObject";
	}
	//登录
	public String login() {
		Person person=personService.getByPersonSn(personSn);
		Boolean right=false;
		if(person!=null){
			if(password.equals(person.getPassword())){
				right=true;
			}else{
				right=false;
			}
		}
		if(right){
			session.put("personSn", person.getPersonSn());			
			session.put("personName", person.getPersonName());
			jsonObject.put("status", "ok");
		}else{
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//安全退出
	public String exit(){
		session.clear();
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
	public String getPersonSn() {
		return personSn;
	}
	public void setPersonSn(String personSn) {
		this.personSn = personSn;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	public String getOldPersonSn() {
		return oldPersonSn;
	}

	public void setOldPersonSn(String oldPersonSn) {
		this.oldPersonSn = oldPersonSn;
	}
}
