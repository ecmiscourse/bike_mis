package cn.easybike.dao.impl;

import org.springframework.stereotype.Repository;

import cn.easybike.dao.BikeDao;
import cn.easybike.entity.Bike;
/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:17:38 马辉 新建
*/
@Repository("bikeDao")
public class BikeDaoImpl extends BaseDaoImpl<Bike> implements BikeDao {


}
