package cn.easybike.action;

import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.easybike.service.ResourceService;

public class BaseAction<T> extends ActionSupport implements RequestAware,
SessionAware,ApplicationAware,ModelDriven<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����ʵ�屸��
	protected T entity;
	//���Դ��Ͷ��id�����ֶ� ��ʽ�Զ��Ÿ���
	protected String ids;
	//page���ڽ��տͻ��˴��ݵ�ҳ��
	protected Integer page;
	//rows���ڽ��տͻ��˴��ݵ�ÿҳ����
	protected Integer rows;
	//���ڷ�װ����request
	protected Map<String, Object> request;
	//���ڷ�װ�Ựsession
	protected Map<String, Object> session;
	//���ڷ�װapplication
	protected Map<String, Object> application;
	//Serviceע��
	
	@Resource(name="resourceService")
	protected ResourceService resourceService;
	
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
