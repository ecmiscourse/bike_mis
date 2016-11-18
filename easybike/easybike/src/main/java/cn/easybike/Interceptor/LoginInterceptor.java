package cn.easybike.Interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.easybike.action.PersonAction;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WebApplicationContext springContext;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		springContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		String result = "";
		HttpServletRequest request=ServletActionContext.getRequest();
		String path=request.getRequestURI();
		String contextPath=ServletActionContext.getServletContext().getContextPath();
		String actionUrl=path.replace(contextPath, "");
		
		if (PersonAction.class == invocation.getAction().getClass()&&invocation.getProxy().getActionName().equals("personAction_login")) {
			return invocation.invoke();
		}
		
		if (ServletActionContext.getRequest().getSession().getAttribute("personSn") == null) {
			return "login";
		}
		result = invocation.invoke();
		return result;
	}

}
