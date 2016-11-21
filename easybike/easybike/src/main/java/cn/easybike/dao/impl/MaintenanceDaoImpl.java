package cn.easybike.dao.impl;

import org.springframework.stereotype.Repository;

import cn.easybike.dao.MaintenanceDao;
import cn.easybike.entity.Maintenance;

/**
* dao<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:35:39 马辉 新建
*/
@Repository("maintenanceDao")
public class MaintenanceDaoImpl extends BaseDaoImpl<Maintenance> implements MaintenanceDao {

}
