package com.learning.www.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learning.www.entity.ZphInfo;
import com.learning.www.mapper.ZphInfoMapper;
import com.learning.www.service.ZphInfoService;
import com.learning.www.utils.GuavaCache;

@Service
public class ZphInfoServiceImpl implements ZphInfoService{

	@Autowired
	private ZphInfoMapper zphinfomapper;
	
	private static Logger logger = LoggerFactory.getLogger(ZphInfoServiceImpl.class);
	
	@Override
	//@Cacheable(value = "ZphInfo",key="'List_zphinfo'")
	public List<ZphInfo> getZphInfoList() {
		// 先从缓存中取数据，如果没有再从数据库取
		if(null != GuavaCache.getAllInfo() && !GuavaCache.getAllInfo().isEmpty()) {
			return GuavaCache.getAllInfo();
		}
		List<ZphInfo> zphinfolist = zphinfomapper.getZphInfoList();
		for (ZphInfo zphInfo : zphinfolist) {
			GuavaCache.setKey(zphInfo.getId(), zphInfo);
		}
		
		return zphinfolist;
	}
	
	@Override
	public int postZphInfo(ZphInfo zphinfo) {
		int ret = zphinfomapper.postZphInfo(zphinfo);
		if(ret == 1) {
			zphinfo = getZphInfoByTitle(zphinfo.getTitle());
			// 新增的数据添加到缓存中
			GuavaCache.setKey(zphinfo.getId(), zphinfo);
		}else {
			logger.info("service添加招聘会信息失败:"+zphinfo.toString());
			return 0;
		}

		return ret;
	}

	@Override
	//@CacheEvict(value = "ZphInfoById", key = "#id")
	public int deleteZphInfo(int id) {
		// 删除缓存中的数据，再删除数据库中的数据
		GuavaCache.delcache(id);
		int ret = zphinfomapper.deleteZphInfo(id);
		return ret;
	}

	@Override
	//@Cacheable(value = "ZphInfoById", key = "#id")
	public ZphInfo getZphInfoById(int id) {
		// 查找缓存，其中 招聘会标题不能为null
		if(null != GuavaCache.getKey(id).getTitle() && !GuavaCache.getKey(id).getTitle().equals("")) {  
			//logger.info(GuavaCache.getKey(id).toString());
			return GuavaCache.getKey(id);
		}
		return zphinfomapper.getZphInfoById(id);
		
	}

	@Override
	public ZphInfo getZphInfoByTitle(String title) {
		return zphinfomapper.getZphInfoByTitle(title);
	}

	@Override
	public int putZphInfoById(ZphInfo zphinfo) {

		int ret = zphinfomapper.putZphInfoById(zphinfo);
		if(ret == 1) {
			GuavaCache.setKey(zphinfo.getId(), zphinfo);
		}
		return ret;
	}

	@Override
	public int putZphStateById(ZphInfo zphinfo) {
		int ret = zphinfomapper.putZphStateById(zphinfo);
		if(ret == 1) {
			GuavaCache.setKey(zphinfo.getId(), zphinfo);
		}
		return ret;
	}

	@Override
	public ZphInfo getZphInfoByIdAndState(int id) {
		return zphinfomapper.getZphInfoByIdAndState(id);
	}
	

}
