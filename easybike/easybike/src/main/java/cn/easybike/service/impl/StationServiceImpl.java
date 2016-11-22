package cn.easybike.service.impl;

import org.springframework.stereotype.Service;

import cn.easybike.entity.Station;
import cn.easybike.service.StationService;
/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:51:56 马辉 新建
*/
@Service("stationService")
public class StationServiceImpl extends BaseServiceImpl<Station> implements StationService {

	@Override
	public Station getByStationSn(String stationSn) {
		return stationDao.getByStationSn(stationSn);
	}

	//删除
	@Override
	public void deleteBySn(String stationSn) {
		stationDao.deleteBySn(stationSn);
	}

}
