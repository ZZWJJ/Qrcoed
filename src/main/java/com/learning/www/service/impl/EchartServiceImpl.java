package com.learning.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.www.entity.EchartJobAmount;
import com.learning.www.entity.EchartUser;
import com.learning.www.mapper.EchartMapper;
import com.learning.www.service.EchartService;

@Service
public class EchartServiceImpl implements EchartService{

	@Autowired
	EchartMapper echartMapper;
	
	@Override
	public EchartUser getComAmount() {
		return echartMapper.getComAmount();
	}

	@Override
	public EchartUser getWxuserAmount() {
		return echartMapper.getWxuserAmount();
	}

	@Override
	public List<EchartJobAmount> getJobAmount() {
		return echartMapper.getJobAmount();
	}

}
