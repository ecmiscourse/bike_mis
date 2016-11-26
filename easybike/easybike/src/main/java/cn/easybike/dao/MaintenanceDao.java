package cn.easybike.dao;

import cn.easybike.entity.Maintenance;

public interface MaintenanceDao extends BaseDao<Maintenance> {

	public 	Maintenance getByMaintenanceSn(String maintenanceSn);

}
