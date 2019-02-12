package com.learning.www.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.learning.www.entity.EchartJobAmount;
import com.learning.www.entity.EchartUser;

@Mapper
public interface EchartMapper {
	
	@Select("select count(id) as amount from company")
	public EchartUser getComAmount();
	
	@Select("select count(id) as amount from wxuser")
	public EchartUser getWxuserAmount();
	
	@Select("select count( wxuser_job.jobid ) as amount,wxuser_job.jobid from wxuser_job, job where wxuser_job.jobid = job.id group by wxuser_job.jobid order by amount desc limit 10")
	public List<EchartJobAmount> getJobAmount();
}
