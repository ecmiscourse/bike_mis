package cn.easybike.action;

import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.easybike.service.PersonService;
import cn.easybike.service.ResourceService;

public class DailyAction<T> extends ActionSupport implements RequestAware,
SessionAware,ApplicationAware,ModelDriven<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//泛型实体备用
	protected T entity;
	//可以传送多个id给该字段 形式以逗号隔开
	protected String ids;
	//page用于接收客户端传递的页码
	protected Integer page;
	//rows用于接收客户端传递的每页行数
	protected Integer rows;
	//用于封装请求request
	protected Map<String, Object> request;
	//用于封装会话session
	protected Map<String, Object> session;
	//用于封装application
	protected Map<String, Object> application;
	//Service注入
	
	@Resource(name="resourceService")
	protected ResourceService resourceService;
	@Resource(name="personService")
	protected PersonService personService;
	
	@Override
	public T getModel() {
		return entity;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
