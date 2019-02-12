package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.EchartComAmount;
import com.learning.www.entity.EchartJobAmount;
import com.learning.www.entity.EchartUser;
import com.learning.www.entity.EchartZphAmount;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.EchartService;
import com.learning.www.service.JobMapperService;
import com.learning.www.service.Zph2ComService;

@Controller
@RequestMapping("echart")
public class EchartsController {

	@Autowired
	Zph2ComService zphcomservice;
	@Autowired
	EchartService echartService;
	@Autowired
	JobMapperService jobService;
	@Autowired
	CompanyInfoService comService;
	
	/***
	 * 公司数量图表
	 * @return
	 */
	@RequestMapping("getComAmount")
	@ResponseBody
	public List<EchartComAmount> getComAmount() {
		
		List<EchartComAmount> comAmountList = new ArrayList<>();
		
		comAmountList = zphcomservice.getComAmount();
		
		return comAmountList;		
	}
	
	/***
	 * 招聘会数量图表
	 * @return
	 */
	@RequestMapping("getZphAmount")
	@ResponseBody
	public List<EchartZphAmount> getZphAmount(){
		
		List<EchartZphAmount> zphAmountList = new ArrayList<>();
		
		zphAmountList = zphcomservice.getZphAmount();
		
		return zphAmountList;		
		
	}
	
	/***
	 * 手机用户和企业用户数量
	 * @return
	 */
	@RequestMapping("getCount")
	@ResponseBody
	public List<EchartUser> getCount(){
		
		List<EchartUser> echartAmountList = new ArrayList<EchartUser>();
		EchartUser comAmount = echartService.getComAmount();
		comAmount.setName("企业");
		EchartUser wxUserAmount = echartService.getWxuserAmount();
		wxUserAmount.setName("求职者");
		echartAmountList.add(wxUserAmount);
		echartAmountList.add(comAmount);
		
		return echartAmountList;	
	}
	
	@RequestMapping("getJobAmount")
	@ResponseBody
	public List<EchartJobAmount> getJobAmount(){
		
		List<EchartJobAmount> jobList = new ArrayList<EchartJobAmount>();
		List<EchartJobAmount> NEW_jobList = new ArrayList<EchartJobAmount>();
		jobList = echartService.getJobAmount();
		for (EchartJobAmount echartJobAmount : jobList) {
			
			echartJobAmount.setJobname(jobService.getJobById(echartJobAmount.getJobid()).getJobname());
			
			echartJobAmount.setCname(comService.getComInfoById(jobService.getJobById(echartJobAmount.getJobid()).getComid()).getCname());
			
			NEW_jobList.add(echartJobAmount);
		}
		
		return NEW_jobList;
		
	}
	
	
	
	
	
	
}
