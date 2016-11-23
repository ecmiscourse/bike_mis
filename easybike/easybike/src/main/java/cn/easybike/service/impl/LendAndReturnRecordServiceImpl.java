package cn.easybike.service.impl;


import org.springframework.stereotype.Service;
import cn.easybike.entity.LendAndReturnRecord;
import cn.easybike.service.LendAndReturnRecordService;

@Service("lendAndReturnRecordService")
public class LendAndReturnRecordServiceImpl extends BaseServiceImpl<LendAndReturnRecord> implements LendAndReturnRecordService {

	@Override
	public void deleteByRecordSn(String recordSn) {
		lendAndReturnRecordDao.deleteByRecordSn(recordSn);
	}

	
}
