package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.Job;
import com.learning.www.service.JobMapperService;

@Controller
@RequestMapping("job")
public class JobController {	
	
	@Autowired
	JobMapperService jobservice;	
	
	private static Logger logger = LoggerFactory.getLogger(JobController.class);
	/***
	 * 根据公司id 查询公司岗位信息
	 * @param comid
	 * @return
	 */
	@RequestMapping("getJobList")
	@ResponseBody
	public List<Job> getJobList(int comid){
		
		List<Job> jobList = new ArrayList<Job>();
		jobList = jobservice.getJobByComId(comid);
				
		return jobList;		
	}
	
	/***
	 * post 新增公司岗位信息
	 * @param job
	 * @return
	 */
	@RequestMapping("postJob")
	@ResponseBody
	public int postJob(Job job) {
		
		int ret = jobservice.postJob(job);		
		
		return ret;
	}
	
	/***
	 * del 删除岗位
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteJob")
	@ResponseBody
	public int deleteJob(int id) {
		
		int ret = jobservice.deleteJob(id);		
		return ret;
	}
	
	/***
	 * put 更新岗位信息
	 * @param job
	 * @return
	 */
	@RequestMapping("putJob")
	@ResponseBody
	public int putJob(Job job) {
		
		logger.info(job.toString());
		int ret = jobservice.putJobById(job);
		
		return ret;
	}
		
	
}
