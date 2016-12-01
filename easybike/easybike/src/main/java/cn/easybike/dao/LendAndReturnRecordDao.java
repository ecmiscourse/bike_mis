package cn.easybike.dao;


import cn.easybike.entity.LendAndReturnRecord;

public interface LendAndReturnRecordDao extends BaseDao<LendAndReturnRecord> {
	 public void deleteByRecordSn(String recordSn);
	   public LendAndReturnRecord getByStudentId(String studentId);
	   public LendAndReturnRecord getByRecordSn(String recordSn);
	   
}

