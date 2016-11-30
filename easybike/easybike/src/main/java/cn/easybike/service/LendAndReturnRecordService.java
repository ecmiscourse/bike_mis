package cn.easybike.service;

import cn.easybike.entity.LendAndReturnRecord;

public interface LendAndReturnRecordService extends BaseService<LendAndReturnRecord> {
	   public void deleteByRecordSn(String recordSn);
       public LendAndReturnRecord getByStudentId(String studentId);
       public LendAndReturnRecord getByRecordSn(String recordSn);
}

