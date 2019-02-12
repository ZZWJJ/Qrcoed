package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.Job_User;
import com.learning.www.entity.WxUser;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.JobMapperService;
import com.learning.www.service.WxUserService;

@RequestMapping("wxcom")
@Controller
public class WxComController {
	
	@Autowired
	CompanyInfoService comService;
	@Autowired
	WxUserService wxuserService;
	@Autowired
	JobMapperService jobService;
	/***
	 * 公司管理求职信息页面
	 * @return
	 */
	@RequestMapping("toWxCom")
	public String toWxUserAdmin(Model model) {
		//登录信息
		Subject sub = SecurityUtils.getSubject();
		String loginName = (String) sub.getPrincipal();
		
		int comid = comService.getComidByCname(loginName);
		List<Job_User> jobUserList = new ArrayList<Job_User>();
		List<WxUser> wxuserList = new ArrayList<WxUser>();
		WxUser wxuser = new WxUser();
		jobUserList = comService.getResumInfo(comid);
		for (Job_User job_User : jobUserList) {
			wxuser = wxuserService.getWxUserById(job_User.getUserid());
			wxuser.setJobname(jobService.getJobById(job_User.getJobid()).getJobname());
			wxuser.setJobid(job_User.getJobid());
			wxuserList.add(wxuser);
		}
		
		model.addAttribute("loginName", loginName);
		model.addAttribute("wxuserList", wxuserList);
		
		return "wxuser/com";
	}
	
	/***
	 * 企业删除简历
	 * @param userid
	 * @param jobid
	 * @return
	 */
	@RequestMapping("delResum")
	@ResponseBody
	public int delResum(@RequestParam("userid")int userid,@RequestParam("jobid")int jobid) {
				
		int ret = wxuserService.delResum(userid, jobid);		
		return ret;		
	}	
}
