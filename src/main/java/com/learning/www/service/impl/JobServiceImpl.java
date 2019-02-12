package com.learning.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.www.entity.Job;
import com.learning.www.mapper.JobMapper;
import com.learning.www.service.JobMapperService;

@Service
public class JobServiceImpl implements JobMapperService{

	@Autowired
	JobMapper jobmapper;
	
	
	@Override
	public List<Job> getJobByComId(int comid) {
		return jobmapper.getJobByComId(comid);
	}

	@Override
	public int postJob(Job job) {
		return jobmapper.postJob(job);
	}

	@Override
	public int deleteJob(int id) {
		return jobmapper.deleteJob(id);
	}

	@Override
	public int putJobById(Job job) {
		return jobmapper.putJobById(job);
	}

	@Override
	public Job getJobById(int id) {
		return jobmapper.getJobById(id);
	}

}
