package cn.easybike.action;

import org.apache.struts2.ServletActionContext;

import cn.easybike.entity.Person;
import net.sf.json.JSONObject;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午5:48:21 马辉 新建
*/
public class PersonAction extends BaseAction<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personSn;
	private String password;
	private JSONObject jsonObject=new JSONObject();
	
	
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
}
