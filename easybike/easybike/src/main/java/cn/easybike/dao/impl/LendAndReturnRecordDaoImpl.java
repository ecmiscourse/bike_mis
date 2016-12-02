package cn.easybike.dao.impl;

import org.springframework.stereotype.Repository;

import cn.easybike.dao.LendAndReturnRecordDao;
import cn.easybike.entity.LendAndReturnRecord;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:28:50 马辉 新建
*/

@Repository("lendAndReturnRecordDao")
public class LendAndReturnRecordDaoImpl extends BaseDaoImpl<LendAndReturnRecord> implements LendAndReturnRecordDao {

	@Override
	public void deleteByRecordSn(String recordSn) {
		String hql="delete LendAndReturnRecord l where l.recordSn=:recordSn";
		getSession().createQuery(hql).setString("recordSn", recordSn).executeUpdate();
	}

	@Override
	public LendAndReturnRecord getByStudentId(String studentId) {
		String hql="select l from LendAndReturnRecord l where l.studentId=:studentId and l.isHasReturned=false";
		return (LendAndReturnRecord) getSession().createQuery(hql).setString("studentId", studentId).uniqueResult();
	}
	@Override
	public LendAndReturnRecord getByRecordSn(String recordSn) {
		String hql="select l from LendAndReturnRecord l where l.recordSn=:recordSn";
		return (LendAndReturnRecord) getSession().createQuery(hql).setString("recordSn", recordSn).uniqueResult();
	}

}
