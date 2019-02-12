package com.learning.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.www.entity.Job;

@Service
public interface JobMapperService {
	
	public List<Job> getJobByComId(int comid);
	
	public int postJob(Job job);
	
	public int deleteJob(int id);
	
	public int putJobById(Job job);
	
	public Job getJobById(int id);

}
