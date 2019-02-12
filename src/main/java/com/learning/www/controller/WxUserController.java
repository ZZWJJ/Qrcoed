package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.ComInfo;
import com.learning.www.entity.Job;
import com.learning.www.entity.User;
import com.learning.www.entity.WxUser;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.JobMapperService;
import com.learning.www.service.UserMapperService;
import com.learning.www.service.WxUserService;

@Controller
@RequestMapping("wxuser")
public class WxUserController {
	
	@Autowired
	CompanyInfoService comservice;
	@Autowired
	JobMapperService jobservice;	
	@Autowired
	WxUserService wxuserservice;
	@Autowired
	UserMapperService userservice;
	
	/***
	 * 得到首页信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("getComInfoById")
	public String getComInfoById(String id,Model model) {
		// 公司信息
		int comid = Integer.parseInt(id);
		ComInfo cominfo = comservice.getComInfoById(comid);
		// 岗位信息
		List<Job> jobList = new ArrayList<Job>();
		jobList = jobservice.getJobByComId(comid);
		//登录信息
		Subject sub = SecurityUtils.getSubject();
		String loginName = (String) sub.getPrincipal();
				
		model.addAttribute("cominfo", cominfo);
		model.addAttribute("jobList", jobList);
		model.addAttribute("loginName", loginName);
		
		return "wxuser/index";	
	}
	
	/***
	 * 注册页面
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister(Model model) {
		//登录信息
		Subject sub = SecurityUtils.getSubject();
		String loginName = (String) sub.getPrincipal();
		
		model.addAttribute("loginName", loginName);
		return "wxuser/register";	
	}
	
	/***
	 * 简历注册
	 * @return
	 */
	@RequestMapping("Register")
	@ResponseBody
	public int register(WxUser wxuser) {		
		
		if(null != wxuserservice.getWxUserByTelphone(wxuser.getTelphone())) {
			return 4;// 4: 代表 此号码已经注册过
		}
		if(null == wxuser.getTelphone() || wxuser.getTelphone().equals("") || wxuser.getTelphone().length() < 11){
			return 3;// 3: 代表手机号码为空
		}
		int ret = wxuserservice.postWxUser(wxuser);
		User user = new User();
		user.setUsername(wxuser.getTelphone());
		String password = wxuser.getTelphone().substring(5);
		user.setPassword(password);
		user.setType(0);//0 : 代表 微信用户
		userservice.postUserInfo(user);
				
		return ret;	
	}

	/***
	 * 登录页面
	 * @return
	 */
	@RequestMapping("toWxLogin")
	public String wxLogin() {		
		return "login";
	}
	
	/***
	 * 我的信息页面
	 * @return
	 */
	@RequestMapping("toMyInfo")
	public String myInfo(Model model) {
		
		//登录信息
		Subject sub = SecurityUtils.getSubject();
		String loginName = (String) sub.getPrincipal();
		//用户信息
		WxUser wxuser = wxuserservice.getWxUserByTelphone(loginName);
		
		model.addAttribute("loginName", loginName);
		model.addAttribute("wxuserinfo", wxuser);
		return "wxuser/myinfo";
	}
	
	/***
	 * 求职者更新简历信息
	 * @param wxuser
	 * @return
	 */
	@RequestMapping("putMyInfo")
	@ResponseBody
	public int putMyInfo(WxUser wxuser) {
		
		int ret = wxuserservice.putWxUserById(wxuser);		
		return ret;
	}
	
	/***
	 * 简历投递信息
	 * @param jobid
	 * @return
	 */
	@RequestMapping("sendMyInfo")
	@ResponseBody
	public int sendMyInfo(String jobid) {
		
		int job_id = Integer.parseInt(jobid); 
		int ret = 1;
		//登录信息
		Subject sub = SecurityUtils.getSubject();				
//		if(null == sub || sub.equals("")) {
//			return 2; // 2 ：未登陆
//		}
		String loginName = (String) sub.getPrincipal();
		WxUser wxuser = null;
		if(wxuserservice.getWxUserByTelphone(loginName) == null) {			
			return 2; // 2 ：未登陆
		}else {
			wxuser = wxuserservice.getWxUserByTelphone(loginName);
			ret = wxuserservice.postMyInfo(job_id, wxuser.getId());
		}
		
		return ret;	
	}
	
	
	
	
	
	
	
}
