package com.learning.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.www.entity.ComInfo;
import com.learning.www.entity.Job_User;
import com.learning.www.mapper.ComInfoMapper;
import com.learning.www.service.CompanyInfoService;

@Service
public class ComInfoServiceImpl implements CompanyInfoService{

	@Autowired
	private ComInfoMapper cominfomapper;

	@Override
	public List<ComInfo> getComInfoList() {
		return cominfomapper.getComInfoList();
	}

	@Override
	public ComInfo getComInfoById(int id) {
		return cominfomapper.getComInfoById(id);
	}

	@Override
	public List<ComInfo> getComInfoByUid(int uid) {
		return cominfomapper.getComInfoByUid(uid);
	}

	@Override
	public int postComInfo(ComInfo cominfo) {
		return cominfomapper.postComInfo(cominfo);
	}

	@Override
	public int deleteComInfo(int id) {
		return cominfomapper.deleteComInfo(id);
	}

	@Override
	public int putComInfoById(ComInfo cominfo) {
		return cominfomapper.putComInfoById(cominfo);
	}

	@Override
	public int putUserById(int uid, String uname, int id) {
		return cominfomapper.putUserById(uid, uname, id);
	}

	@Override
	public String getComNameInfoById(int id) {
		return cominfomapper.getComNameInfoById(id);
	}

	@Override
	public int getComidByCname(String cname) {
		return cominfomapper.getComidByCname(cname);
	}

	@Override
	public List<Job_User> getResumInfo(int comid) {
		return cominfomapper.getResumInfo(comid);
	}

	@Override
	public String getComByCname(String cname) {
		// TODO Auto-generated method stub
		return cominfomapper.getComByCname(cname);
	}

	@Override
	public List<ComInfo> getComList() {
		// TODO Auto-generated method stub
		return cominfomapper.getComList();
	}


	
	
	
}
