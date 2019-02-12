package com.learning.www.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.learning.www.entity.ZphInfo;


public class GuavaCache {
	private static Logger logger = LoggerFactory.getLogger(GuavaCache.class);
	
	private static LoadingCache<Integer, ZphInfo> cache = CacheBuilder.newBuilder()
			.maximumSize(500)//最多存放十个数据
			.expireAfterWrite(20, TimeUnit.MINUTES)//缓存，10秒之后进行回收
			.recordStats()//开启，记录状态数据功能
			.build(new CacheLoader<Integer, ZphInfo>() {
				//数据加载，默认返回-1，也可以是查询操作，如从DB查询  
				// 默认数据加载实现，当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载。
				ZphInfo zph = new ZphInfo();
				@Override
				public ZphInfo load(Integer key) throws Exception {
					// TODO Auto-generated method stub
					logger.info("缓存load函数，未得到数据，返回-1");
					return zph;
				}
			});
	// 设置缓存的key和value
	public static void setKey(Integer key,ZphInfo value) {
		cache.put(key, value);
	}

	public static int delcache(Integer key) {
		cache.invalidate(key);
		return 1;
	}
	// 根据key获取缓存
	public static ZphInfo getKey(Integer key) {
		ZphInfo value = null;
		
		try {
			value = cache.get(key);
			if(null == value) {
				return null;
			}
			//logger.info(value.toString());
			return value;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			logger.info("获取缓存出错");
			e.printStackTrace();
		}
		return null;
	}
	// 查看缓存中所有的数据
	public static List<ZphInfo> getAllInfo() {
		// 查看所有数据map
		ConcurrentMap<Integer, ZphInfo> map = cache.asMap();
		// 将map放入collections中
		Collection<ZphInfo> list = map.values();
		// 将collections中的value放入list中返回
		List<ZphInfo> zphinfolist = new ArrayList<ZphInfo>();
		for (ZphInfo zphInfo : list) {
			if(null != zphInfo.getTitle() && !zphInfo.getTitle().equals("")) {
				zphinfolist.add(zphInfo);
			}
			
		}
		
		return zphinfolist;		
	}
}
