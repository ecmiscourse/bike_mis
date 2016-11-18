package cn.easybike.action;

import org.apache.struts2.ServletActionContext;

import cn.easybike.entity.Person;
import net.sf.json.JSONObject;

/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��18������5:48:21 ��� �½�
*/
public class PersonAction extends BaseAction<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personSn;
	private String password;
	private JSONObject jsonObject=new JSONObject();
	
	
	//��¼
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
	//��ȫ�˳�
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
