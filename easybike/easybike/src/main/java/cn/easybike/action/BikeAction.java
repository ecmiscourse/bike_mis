package cn.easybike.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.easybike.entity.Bike;
import cn.easybike.entity.Person;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:26:42 马辉 新建
*/
public class BikeAction extends BaseAction<Bike> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stationSn;
	private JSONObject jsonObject=new JSONObject();
	private String bikeSn;
	private String startDate;
	private String oldBikeSn;
	private File uploadExcel;
	private String uploadExcelContentType;
	private String uploadExcelFileName;
	private InputStream excelStream; 
    private String excelFileName;
	//车辆导出
    public String export(){
    	XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("车辆信息");
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
		sheet.setColumnWidth(0, 80*80);
		sheet.setColumnWidth(1, 80*80);
		sheet.setColumnWidth(2, 80*80);
		sheet.setColumnWidth(3, 80*80);
		XSSFCell cell;
		//设置标题
		row=sheet.createRow(0);
		cell=row.createCell(0);
    	cell.setCellValue("车辆编号");
    	cell=row.createCell(1);
    	cell.setCellValue("开始使用日期");
    	cell=row.createCell(2);
    	cell.setCellValue("当前车辆状态");
    	cell=row.createCell(3);
    	cell.setCellValue("当前所在站点");
    	//循环插入数据
    	int size=0;
    	for(Bike bike:bikeService.queryAll()){
    		size++;
    		row=sheet.createRow(size);
    		cell=row.createCell(0);
        	cell.setCellValue(bike.getBikeSn());
        	
        	cell=row.createCell(1);
        	cell.setCellValue(bike.getStartDate().toString());
        	
        	cell=row.createCell(2);
        	if(bike.getStatus()==(byte)0){
        		cell.setCellValue("可借");
        	}else if(bike.getStatus()==(byte)1){
        		cell.setCellValue("借出");
        	}else if(bike.getStatus()==(byte)2){
        		cell.setCellValue("维修中");
        	}else if(bike.getStatus()==(byte)3){
        		if(bike.getEndDate()!=null){
        			cell.setCellValue("报废，报废时间："+bike.getEndDate().toString());
        		}else{
        			cell.setCellValue("报废");
        		}
        		
        	}        	
        	cell=row.createCell(3);
        	if(bike.getStation()!=null){
        		cell.setCellValue(bike.getStation().getStationName());
        	} 	
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
            excelFileName =URLEncoder.encode("车辆信息表.xlsx", "UTF-8"); 	      
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
		return "export";
    }
	//车辆批量导入
	public String importBike(){
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
		
		String sn="";
		String error="";
		int errornum=0;
		XSSFSheet xssfSheet = wb.getSheetAt(0);
		float m1=xssfSheet.getLastRowNum();
		for(int rowNum=1;rowNum<=xssfSheet.getLastRowNum();rowNum++){
			XSSFRow row=xssfSheet.getRow(rowNum);
			float m2=rowNum;
			session.put("progressValue",(int)(m2/m1*100));
			Bike bike=new Bike();
			try{
				//编号
				XSSFCell bikeSn=row.getCell(0);
				if(bikeSn!=null&&bikeSn.toString().trim().length()>0){
					sn=bikeSn.getStringCellValue();
					if(bikeService.getByBikeSn(sn)==null){
						bike.setBikeSn(sn);
					}else{
						errornum++;
						error+="第"+(rowNum+1)+"行编号已经存在，导入失败！";
						continue;
					}
				}else{
					errornum++;
					error+="第"+(rowNum+1)+"行出现空值，导入失败！";
					continue;
				}
				
				//日期
				XSSFCell start=row.getCell(1);
				if(start!=null&&start.toString().trim().length()>0){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
					try{
						bike.setStartDate(LocalDate.parse(sdf.format(start.getDateCellValue()).toString()));
					}catch(Exception e){
						errornum++;
						error+="第"+(rowNum+1)+"行数据非日期格式，导入失败！";
						continue;
					}
				}else{
					bike.setStartDate(LocalDate.now());
				}
				
				bike.setStation(stationService.getByStationSn("0001"));
				bike.setStatus((byte) 0);
				bikeService.save(bike);
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
	//分页查询
	public String queryByPage(){
		String hql="select b from Bike b";
		JSONArray array=new JSONArray();
		if(stationSn!=null&&stationSn.trim().length()>0){
			hql+=" where b.station.stationSn='"+stationSn+"'";
		}
		for(Bike bike:bikeService.queryByPage(hql, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("bikeSn", bike.getBikeSn());
			jo.put("startDate", bike.getStartDate().toString());
			jo.put("status", bike.getStatus());
			if(bike.getStation()!=null){
				jo.put("station", bike.getStation().getStationName());
			}
			if(bike.getEndDate()!=null){
				jo.put("endDate", bike.getEndDate().toString());
			}
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", bikeService.countByHql(hql.replaceFirst("b", "count(b)")));
		return "jsonObject";
	}
	//添加
	public String save(){
		jsonObject.put("status", "ok");
		Bike bike=new Bike();
		try{
			bike.setBikeSn(bikeSn);
			bike.setStartDate(LocalDate.parse(startDate));
			bike.setStation(stationService.getByStationSn("0001"));
			bike.setStatus((byte) 0);
			bikeService.save(bike);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//更新
	public String update(){
		jsonObject.put("status", "ok");
		try{
			Bike bike = bikeService.getByBikeSn(oldBikeSn);
			bike.setBikeSn(bikeSn);
			bike.setStartDate(LocalDate.parse(startDate));
			bikeService.update(bike);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		
		return "jsonObject";
	}
	
	//删除
	public String delete(){
		jsonObject.put("status", "ok");
		try{
			bikeService.deleteBySn(bikeSn);
		}catch(Exception e){
			jsonObject.put("status", "nook");
		}
		return "jsonObject";
	}
	//验证编号是否存在
	public String isExist(){
		if(bikeService.getByBikeSn(bikeSn)!=null){
			jsonObject.put("isExist", true);
		}else{
			jsonObject.put("isExist", false);
		}
		return "jsonObject";
	}
	//根据站点得到车辆
	public String getByStationSn(){
		String hql1="select b from Bike b where b.station.stationSn='"+stationSn+"'";
		String hql2="select count(b) from Bike b where b.station.stationSn='"+stationSn+"'";
		JSONArray array=new JSONArray();
		for(Bike bike:bikeService.queryByPage(hql1, page, rows)){
			JSONObject jo=new JSONObject();
			jo.put("bikeSn", bike.getBikeSn());
			jo.put("startDate", bike.getStartDate().toString());
			array.add(jo);
		}
		jsonObject.put("rows", array);
		jsonObject.put("total", bikeService.countByHql(hql2));
		return "jsonObject";
	}
	public String getStationSn() {
		return stationSn;
	}
	public void setStationSn(String stationSn) {
		this.stationSn = stationSn;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public String getBikeSn() {
		return bikeSn;
	}
	public void setBikeSn(String bikeSn) {
		this.bikeSn = bikeSn;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getOldBikeSn() {
		return oldBikeSn;
	}
	public void setOldBikeSn(String oldBikeSn) {
		this.oldBikeSn = oldBikeSn;
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
