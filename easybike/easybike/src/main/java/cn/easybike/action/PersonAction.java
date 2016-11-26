package cn.easybike.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.easybike.entity.Person;
import cn.easybike.util.MD5Login;
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
	private File uploadExcel;
	private String uploadExcelContentType;
	private String uploadExcelFileName;
	private InputStream excelStream; 
    private String excelFileName;
	
    
    
	//人员导出
	public String export(){
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("人员");
		XSSFRow row;
		//数据样式
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setFontName("仿宋_GB2312");
		font.setFontHeightInPoints((short)12);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
		for(int i=0;i<4;i++){
			sheet.setDefaultColumnStyle(i, style);
		}
		sheet.setColumnWidth(0, 50*50);
		sheet.setColumnWidth(1, 50*50);
		sheet.setColumnWidth(2, 40*40);
		sheet.setColumnWidth(3, 70*70);
		XSSFCell cell;
		//设置标题
		row=sheet.createRow(0);
		cell=row.createCell(0);
    	cell.setCellValue("姓名");
    	cell=row.createCell(1);
    	cell.setCellValue("学号");
    	cell=row.createCell(2);
    	cell.setCellValue("性别");
    	cell=row.createCell(3);
    	cell.setCellValue("联系方式");
    	//循环插入数据
    	int size=0;
    	for(Person person:personService.queryAll()){
    		size++;
    		row=sheet.createRow(size);
    		cell=row.createCell(0);
        	cell.setCellValue(person.getPersonName());
        	cell=row.createCell(1);
        	cell.setCellValue(person.getPersonSn());
        	cell=row.createCell(2);
        	if(person.getSex()==(byte)0){
        		cell.setCellValue("男");
        	}else{
        		cell.setCellValue("女");
        	}      	
        	cell=row.createCell(3);
        	cell.setCellValue(person.getCellphoneNumber());
    	}
    	
    	try  
        {  
        	ByteArrayOutputStream fout = new ByteArrayOutputStream();  
            wb.write(fout);
            wb.close();
            fout.close();
            byte[] fileContent = fout.toByteArray();  
            ByteArrayInputStream is = new ByteArrayInputStream(fileContent);  
  
            excelStream = is;               
            excelFileName =URLEncoder.encode("人员信息表.xlsx", "UTF-8"); 	      
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
		return "export";
	}
	//人员批量导入
	public String importPerson() throws FileNotFoundException{
		InputStream stream=null;
		try{
			stream=new FileInputStream(uploadExcel);
		}catch(Exception e){
			jsonObject.put("errorNum", 1);
			jsonObject.put("message", "文件读取错误，请确保文件未损坏或格式正确");
			return "jsonObject";
		}
		XSSFWorkbook wb = null;
		try {
			wb=new XSSFWorkbook(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String name="";
		String sn="";
		String cell="";
		String error="";
		int errornum=0;
		XSSFSheet xssfSheet = wb.getSheetAt(0);
		//循环行
		float m1=xssfSheet.getLastRowNum();
		for(int rowNum=1;rowNum<=xssfSheet.getLastRowNum();rowNum++){
			float m2=rowNum;
			session.put("progressValue",(int)(m2/m1*100));
			XSSFRow row=xssfSheet.getRow(rowNum);
			Person person=new Person();
			if(row==null){
				continue;
			}
			try{
				//姓名
				XSSFCell personName=row.getCell(0);
				if(personName!=null&&personName.toString().trim().length()>0){
					name=personName.getStringCellValue();
					person.setPersonName(name);
				}else{
					errornum++;
					error+="第"+(rowNum+1)+"行出现空值，导入失败！";
					continue;
				}
				
				//学号
				XSSFCell personSn=row.getCell(1);
				if(personSn!=null&&personSn.toString().trim().length()>0){
					sn=personSn.getStringCellValue();
					if(personService.getByPersonSn(sn)==null){
						person.setPersonSn(sn);
					}else{
						errornum++;
						error+="第"+(rowNum+1)+"行学号已经存在，导入失败！";
						continue;
					}
				}else{
					errornum++;
					error+="第"+(rowNum+1)+"行出现空值，导入失败！";
					continue;
				}
				
				//性别
				XSSFCell sex=row.getCell(2);
				if(sex!=null&&sex.toString().trim().length()>0){
					if(sex.getStringCellValue().equals("男")){
						person.setSex((byte) 0);
					}else{
						person.setSex((byte) 1);
					}
				}else{
					errornum++;
					error+="第"+(rowNum+1)+"行出现空值，导入失败！";
					continue;
				}
				//联系方式
				XSSFCell cellphone=row.getCell(3); 
				if(cellphone!=null&&cellphone.toString().trim().length()>0){
					DecimalFormat df = new DecimalFormat("0");   
					cell = df.format(cellphone.getNumericCellValue());
					person.setCellphoneNumber(cell);
				}else{
					errornum++;
					error+="第"+(rowNum+1)+"行出现空值，导入失败！";
					continue;
				}
				
				person.setPassword(MD5Login.getMD5Str("123456", null));
				personService.save(person);
			}catch(Exception e){
				errornum++;
				error+="第"+(rowNum+1)+"行导入失败！";
				continue;
			}
		}
		jsonObject.put("errorNum", errornum);
		jsonObject.put("message", error);
		session.put("progressValue",0);
		return "jsonObject";
				
	}
	
	//查询进度
	public String importSession(){
		jsonObject.put("value", (int) session.get("progressValue"));
		return "jsonObject";
	}
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
			person.setPassword(MD5Login.getMD5Str("123456", null));
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
	//验证密码是否正确
	public String isPassword(){
		String personSn = (String) session.get("personSn");
		Person person=personService.getByPersonSn(personSn);
		Boolean right=false;
		if(password.equals(person.getPassword())){
			right=true;
		}else{
			right=false;
		}
		if(right){
			jsonObject.put("status", "ok");
		}else{
			jsonObject.put("status", "nook");
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
			session.put("progressValue", 0);//进度条初始值
			jsonObject.put("status", "ok");
		}else{
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//修改密码
	public String updatePassword(){
		jsonObject.put("status", "ok");
		String personSn = (String) session.get("personSn");
		Person person=personService.getByPersonSn(personSn);
		try{
			person.setPassword(password);
			personService.update(person);;
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
		
	}
	//显示个人信息
	public String personInfor(){
		String sn=(String) session.get("personSn");
		String hql="select p form Person p where p.personSn='"+sn+"'";
		JSONArray array=new JSONArray();
		for(Person person:personService.queryByPage(hql, 0, 0)){
			JSONObject jo=new JSONObject();
			jo.put("personSn", person.getPersonSn());
			jo.put("personName", person.getPersonName());
			jo.put("sex", person.getSex());
			jo.put("cellphoneNumber", person.getCellphoneNumber());
			array.add(jo);
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
	
	public File getUploadExcel() {
		return uploadExcel;
	}

	public void setUploadExcel(File uploadExcel) {
		this.uploadExcel = uploadExcel;
	}
	public String getUploadExcelContentType() {
		return uploadExcelContentType;
	}
	public void setUploadExcelContentType(String uploadExcelContentType) {
		this.uploadExcelContentType = uploadExcelContentType;
	}
	public String getUploadExcelFileName() {
		return uploadExcelFileName;
	}
	public void setUploadExcelFileName(String uploadExcelFileName) {
		this.uploadExcelFileName = uploadExcelFileName;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
}
