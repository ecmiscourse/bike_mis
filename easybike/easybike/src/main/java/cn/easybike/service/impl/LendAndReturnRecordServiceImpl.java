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

	@Override
	public LendAndReturnRecord getByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return lendAndReturnRecordDao.getByStudentId(studentId);
	}
	@Override
	public LendAndReturnRecord getByRecordSn(String recordSn) {
		// TODO Auto-generated method stub
		return lendAndReturnRecordDao.getByRecordSn(recordSn);
	}

	
}

