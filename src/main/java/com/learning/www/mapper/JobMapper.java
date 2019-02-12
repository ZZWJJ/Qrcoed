package com.learning.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.learning.www.entity.Job;

@Mapper
public interface JobMapper {
	
	@Select("select * from job where comid = #{comid}")
	public List<Job> getJobByComId(int comid);
	
	@Select("select * from job where id = #{id}")
	public Job getJobById(int id);
	
	@Insert("insert into job(comid,num,jobname,salary,addinfo) values(#{comid},#{num},#{jobname},#{salary},#{addinfo})")
	public int postJob(Job job);
	
	@Delete("delete from job where id = #{id}")
	public int deleteJob(int id);
	
	@Update("update job set num=#{num},jobname=#{jobname},salary=#{salary},addinfo=#{addinfo} where id=#{id}")
	public int putJobById(Job job);
	
}
