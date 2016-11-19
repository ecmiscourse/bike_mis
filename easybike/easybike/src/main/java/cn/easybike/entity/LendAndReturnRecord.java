package cn.easybike.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="lend_and_return_record")
public class LendAndReturnRecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String recordSn;//借还记录表
	private String studentId;//借车人学号
	private String studentName;//借车人姓名
	private String phoneNumber;//借车人联系方式
	private Timestamp lendDateTime; //借出时间
	private Timestamp returnDateTime;//归还时间
	private Boolean isHasReturned;//是否归还
	
	
}
