package com.learning.www.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.learning.www.entity.Job_User;
import com.learning.www.entity.WxUser;

@Service
public interface WxUserService {
	
	public List<WxUser> getWxUserList();
	
	public WxUser getWxUserById(int id);
	
	public int postWxUser(WxUser wxuser);
	
	public int deleteWxUser(int id);
	
	public int putWxUserById(WxUser wxuser);

	public WxUser getWxUserByTelphone(String telphone);
	
	public int postMyInfo(@Param("jobid")int jobid, @Param("userid")int userid);
	
	public List<Job_User> getResumeList();
	
	public String getUsernameByTelphone(String telphone);
	
	public int deleteWxUser_Job(int id);
	
	public int deleteWxUser_JobByUserId(int userid);
	
	public int delResum(int userid,int jobid);
}
