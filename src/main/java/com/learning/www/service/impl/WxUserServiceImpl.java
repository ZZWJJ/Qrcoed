package com.learning.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.www.entity.Job_User;
import com.learning.www.entity.WxUser;
import com.learning.www.mapper.WxUserMapper;
import com.learning.www.service.WxUserService;

@Service
public class WxUserServiceImpl implements WxUserService{

	@Autowired
	WxUserMapper wxusermapper;
	
	@Override
	public List<WxUser> getWxUserList() {
		return wxusermapper.getWxUserList();
	}

	@Override
	public WxUser getWxUserById(int id) {
		return wxusermapper.getWxUserById(id);
	}

	@Override
	public int postWxUser(WxUser wxuser) {
		return wxusermapper.postWxUser(wxuser);
	}

	@Override
	public int deleteWxUser(int id) {
		return wxusermapper.deleteWxUser(id);
	}

	@Override
	public int putWxUserById(WxUser wxuser) {
		return wxusermapper.putWxUserById(wxuser);
	}

	@Override
	public WxUser getWxUserByTelphone(String telphone) {
		return wxusermapper.getWxUserByTelphone(telphone);
	}

	@Override
	public int postMyInfo(int jobid, int userid) {
		return wxusermapper.postMyInfo(jobid, userid);
	}

	@Override
	public List<Job_User> getResumeList() {
		return wxusermapper.getResumeList();
	}

	@Override
	public String getUsernameByTelphone(String telphone) {
		return wxusermapper.getUsernameByTelphone(telphone);
	}

	@Override
	public int deleteWxUser_Job(int id) {
		return wxusermapper.deleteWxUser_Job(id);
	}

	@Override
	public int deleteWxUser_JobByUserId(int userid) {
		return wxusermapper.deleteWxUser_JobByUserId(userid);
	}

	@Override
	public int delResum(int userid,int jobid) {
		return wxusermapper.delResum(userid,jobid);
	}

}
