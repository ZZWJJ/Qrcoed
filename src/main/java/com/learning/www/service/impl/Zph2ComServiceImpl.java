package com.learning.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.www.entity.Com_Zph;
import com.learning.www.entity.EchartComAmount;
import com.learning.www.entity.EchartZphAmount;
import com.learning.www.mapper.Zph2ComMapper;
import com.learning.www.service.Zph2ComService;

@Service
public class Zph2ComServiceImpl implements Zph2ComService{

	@Autowired
	Zph2ComMapper zphcommapper;
	
	
	@Override
	public int postZph2Com(int zphid, int comid) {
		return zphcommapper.postZph2Com(zphid, comid);
	}

	@Override
	public int[] getZph2ComByZphId(int zphid) {
		return zphcommapper.getZph2ComByZphId(zphid);
	}

	@Override
	public int[] getZph2ComByComId(int comid) {
		return zphcommapper.getZph2ComByComId(comid);
	}

	@Override
	public int deleteZph2ComByZphId(int id) {
		return zphcommapper.deleteZph2ComByZphId(id);
	}

	@Override
	public int putJoinStateByZphid(int id,int isjoin) {
		// TODO Auto-generated method stub
		return zphcommapper.putJoinStateByid(id,isjoin);
	}

	@Override
	public Com_Zph getZph2ComByComIdZphId(int comid, int zphid) {
		return zphcommapper.getZph2ComByComIdZphId(comid, zphid);
	}

	@Override
	public int putAddInfoById(int id, String addinfo) {
		return zphcommapper.putAddInfoById(id, addinfo);
	}

	@Override
	public List<EchartComAmount> getComAmount() {
		return zphcommapper.getComAmount();
	}

	@Override
	public List<EchartZphAmount> getZphAmount() {
		return zphcommapper.getZphAmount();
	}

	@Override
	public int deleteZphInfoByZphId(int zphid) {
		return zphcommapper.deleteZphInfoByZphId(zphid);
	}

	@Override
	public int deleteComInfoByComId(int comid) {
		return zphcommapper.deleteComInfoByComId(comid);
	}




}
