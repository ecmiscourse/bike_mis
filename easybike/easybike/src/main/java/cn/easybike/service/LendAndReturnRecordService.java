package cn.easybike.service;

import cn.easybike.entity.LendAndReturnRecord;

public interface LendAndReturnRecordService extends BaseService<LendAndReturnRecord> {
       public void deleteByRecordSn(String recordSn);
}

