package cn.easybike.service.impl;


import org.springframework.stereotype.Service;

import cn.easybike.entity.Bike;
import cn.easybike.service.BikeService;
/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:20:31 马辉 新建
*/
@Service("bikeService")
public class BikeServiceImpl extends BaseServiceImpl<Bike> implements BikeService {


}
