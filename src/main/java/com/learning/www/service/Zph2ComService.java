package com.learning.www.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.www.entity.Com_Zph;
import com.learning.www.entity.EchartComAmount;
import com.learning.www.entity.EchartZphAmount;


@Service
public interface Zph2ComService {

	/***
	 * post 新增公司参与的招聘会
	 * @param zphid
	 * @param comid
	 * @return
	 */
	@Transactional
	public int postZph2Com(int zphid, int comid);
	
	/***
	 * get 根据id查找对应关系
	 * @param zphid
	 * @return
	 */
	public int[] getZph2ComByZphId(int zphid);
	
	/***
	 * get 根据id查找对应关系
	 * @param zphid
	 * @return
	 */
	public int[] getZph2ComByComId(int comid);
	
	/***
	 * del 根据id删除对应关系
	 * @param zphid
	 * @return
	 */
	@Transactional
	public int deleteZph2ComByZphId(int id);
	
	/***
	 * put 更新参展状态
	 * @param zphid
	 * @return
	 */
	@Transactional
	public int putJoinStateByZphid(int id,int isjoin);
	
	/***
	 * get 根据zphid和comid确定场次
	 * @param comid
	 * @param zphid
	 * @return
	 */
	public Com_Zph getZph2ComByComIdZphId(@Param("comid")int comid,@Param("zphid")int zphid);
	
	/***
	 * 更新备注信息
	 * @param id
	 * @param addinfo
	 * @return
	 */
	public int putAddInfoById(@Param("id")int id,@Param("addinfo")String addinfo);
	
	/***
	 * 专员-公司数量
	 * echarts
	 * @return
	 */
	public List<EchartComAmount> getComAmount();
	
	/***
	 * 招聘会-企业数量
	 * @return
	 */
	public List<EchartZphAmount> getZphAmount();
	
	/***
	 * 删除招聘会或企业信息的同时，删除在这批货com中的相关信息
	 * @param zphid
	 * @return
	 */
	public int deleteZphInfoByZphId(int zphid);
	public int deleteComInfoByComId(int comid);
}
