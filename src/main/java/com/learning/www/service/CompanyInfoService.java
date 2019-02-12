package com.learning.www.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.www.entity.ComInfo;
import com.learning.www.entity.Job_User;

@Service
public interface CompanyInfoService {

	/**
	 * 获取公司信息列表
	 * @return
	 */
	public List<ComInfo> getComInfoList();
	
	/***
	 * 根据id获取公司信息
	 * @param id
	 * @return
	 */
	public ComInfo getComInfoById(int id);
	
	/***
	 * 根据uid查找公司信息
	 * @param uid
	 * @return
	 */
	public List<ComInfo> getComInfoByUid(int uid);
	
	/***
	 * post 插入公司信息
	 * @param cominfo
	 * @return
	 */
	@Transactional
	public int postComInfo(ComInfo cominfo);
	
	/***
	 * del 删除公司信息
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteComInfo(int id);
	
	/***
	 * put 更新公司信息（id）
	 * @param cominfo
	 * @return
	 */
	@Transactional
	public int putComInfoById(ComInfo cominfo);
	
	/***
	 * 企业转移
	 * @param uid
	 * @param uname
	 * @param id
	 * @return
	 */
	public int putUserById(@Param("uid")int uid,@Param("uname")String uname,@Param("id")int id);
	
	public String getComNameInfoById(int id);
	
	public int getComidByCname(String cname);
	
	public List<Job_User> getResumInfo(int comid);	
	
	public String getComByCname(String cname);
	
	public List<ComInfo> getComList();
}
