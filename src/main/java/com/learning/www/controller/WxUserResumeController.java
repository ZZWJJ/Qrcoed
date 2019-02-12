package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.Job_User;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.JobMapperService;
import com.learning.www.service.UserMapperService;
import com.learning.www.service.WxUserService;

@Controller
@RequestMapping("resume")
public class WxUserResumeController {
	
	@Autowired
	WxUserService wxuserService;
	@Autowired
	CompanyInfoService comService;
	@Autowired
	JobMapperService jobService;
	@Autowired
	UserMapperService userService;

	/***
	 * 简历投递情况页面
	 * @return
	 */
	@RequestMapping("toResume")
	public String toWxUserAdmin() {
		return "resume/resum";
	}
	
	/***
	 * 简历投递列表
	 * @return
	 */
	@RequestMapping("getJobUserList")
	@ResponseBody
	public List<Job_User> getJobUserList(){
		
		List<Job_User> jobUserList = new ArrayList<Job_User>();
		List<Job_User> job_UserList = new ArrayList<Job_User>();
		jobUserList = wxuserService.getResumeList();
		for (Job_User job_User : jobUserList) {
			// 用户姓名
			if(null == wxuserService.getWxUserById(job_User.getUserid()).getUsername()) {
				return null;
			}else {
				
				String wxUserName = wxuserService.getWxUserById(job_User.getUserid()).getUsername();						
				job_User.setWxusername(wxUserName);
				
				// 岗位名称
				job_User.setJobname(jobService.getJobById(job_User.getJobid()).getJobname());
				// 公司名称
				int comid = jobService.getJobById(job_User.getJobid()).getComid();
				job_User.setCname(comService.getComNameInfoById(comid));
				job_UserList.add(job_User);
			}						
		}		
		
		return job_UserList;
		
		
	}
	
	@RequestMapping("deleteWxUser_Job")
	@ResponseBody
	public int deleteWxUser_Job(@RequestParam("ids[]") int[] ids) {
		int flag = 1;
		for (int id : ids) {
			int ret = wxuserService.deleteWxUser_Job(id);
			if(ret == 0 ) {
				flag = 0;
				return flag;
			}			
		}					
		return flag;
				
	}
	
	
	
}
