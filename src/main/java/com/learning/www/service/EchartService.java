package com.learning.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.www.entity.EchartJobAmount;
import com.learning.www.entity.EchartUser;

@Service
public interface EchartService {

	public EchartUser getComAmount();
	
	public EchartUser getWxuserAmount();
	
	public List<EchartJobAmount> getJobAmount();
}
