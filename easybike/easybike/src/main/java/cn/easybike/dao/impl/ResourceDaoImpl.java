package cn.easybike.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.easybike.dao.ResourceDao;
import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
* 权限Dao实现<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午8:58:14 马辉 新建
*/
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao{
	//获取导航菜单
	@SuppressWarnings("unchecked")
	public JSONArray getMenu(String personSn, String parentResourceSn){
		String hql="";
		JSONArray array=new JSONArray();
		if(parentResourceSn!=null && parentResourceSn.trim().length()>0){
			hql="select distinct r from Resource r where r.parent.resourceSn="+parentResourceSn+" and r.resourceType='menu' order by r.showSequence";
		}else{
			hql="select distinct r from Resource r where r.parent=null and r.resourceType='menu' order by r.showSequence";
		}
		List<Resource> list=new ArrayList<Resource>();
		list=getSession().createQuery(hql).list();
		for(Resource resource : list){
			JSONObject jo=new JSONObject();
			jo.put("id",resource.getId());
			jo.put("text",resource.getResourceName());
			jo.put("url",resource.getUrl());
			if(resource.getChildren().size()>0){
				jo.put("state","closed");
			}else{
				jo.put("state", "open");
			}
			array.add(jo);
		}
		return array;
	}
}
