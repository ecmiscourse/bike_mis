package cn.easybike.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TemplateDownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String templateName;
	private String newFileName;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	//下载导入模板
	public InputStream getInputStream(){
		String path=ServletActionContext.getServletContext().getRealPath("/template");
		File file=new File(path,templateName);
		InputStream inputStream = null;
		try{
			inputStream = FileUtils.openInputStream(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	//获取下载文件名
	public String getDownloadFileName(){
		String downloadFileName="";
		String filename=newFileName;
		//解决中文乱码
		try {
			downloadFileName = URLEncoder.encode(filename,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
