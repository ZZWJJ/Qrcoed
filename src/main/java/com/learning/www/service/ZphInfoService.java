package com.learning.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.www.entity.ZphInfo;

@Service
public interface ZphInfoService {
	
	/***
	 * 查询招聘会信息
	 * @return
	 */
	public List<ZphInfo> getZphInfoList();
	
	/***
	 * 新增招聘会信息
	 * @param zphinfo
	 * @return
	 */
	@Transactional
	public int postZphInfo(ZphInfo zphinfo);
	
	/***
	 * 删除招聘会信息
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteZphInfo(int id);
	
	/***
	 * 根据id查找信息
	 * @param id
	 * @return
	 */
	public ZphInfo getZphInfoById(int id);
	
	/***
	 * 根据title查找招聘会
	 * @param title
	 * @return
	 */
	public ZphInfo getZphInfoByTitle(String title);
	
	/***
	 * 根据id修改招聘会信息
	 * @param zphinfo
	 * @return
	 */
	@Transactional
	public int putZphInfoById(ZphInfo zphinfo);
	
	/***
	 * 根据id修改招聘会状态
	 * @param zphinfo
	 * @return
	 */
	@Transactional
	public int putZphStateById(ZphInfo zphinfo);
	
	/***
	 * 根据id和state筛选出招聘会
	 * @param id
	 * @return
	 */
	public ZphInfo getZphInfoByIdAndState(int id);
}
