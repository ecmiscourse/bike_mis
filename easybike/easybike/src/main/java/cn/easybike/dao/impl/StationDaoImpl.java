package cn.easybike.dao.impl;


import org.springframework.stereotype.Repository;

import cn.easybike.dao.StationDao;
import cn.easybike.entity.Station;
/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:49:32 马辉 新建
*/
@Repository("stationDao")
public class StationDaoImpl extends BaseDaoImpl<Station> implements StationDao {


}
