package com.learning.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.learning.www.entity.Job_User;
import com.learning.www.entity.WxUser;

@Mapper
public interface WxUserMapper {

	@Select("select * from wxuser order by id desc")
	public List<WxUser> getWxUserList();
	
	@Select("select * from wxuser where id = #{id}")
	public WxUser getWxUserById(int id);
	
	@Select("select * from wxuser where telphone = #{telphone}")
	public WxUser getWxUserByTelphone(String telphone);
	
	@Select("select username from wxuser where telphone = #{telphone}")
	public String getUsernameByTelphone(String telphone);
	
	@Insert("insert into wxuser(username,sex,age,telphone,home,school,job) values(#{username},#{sex},"
			+ "#{age},#{telphone},#{home},#{school},#{job})")
	public int postWxUser(WxUser wxuser);
	
	@Delete("delete from wxuser where id = #{id}")
	public int deleteWxUser(int id);
	
	@Update("update wxuser set username=#{username},sex=#{sex},age=#{age},telphone=#{telphone},home=#{home}"
			+ ",school=#{school},job=#{job} where id=#{id}")
	public int putWxUserById(WxUser wxuser);
	
	@Insert("insert into wxuser_job(jobid,userid) values(#{jobid},#{userid})")
	public int postMyInfo(@Param("jobid")int jobid, @Param("userid")int userid);
	
	@Select("select * from wxuser_job order by id asc")
	public List<Job_User> getResumeList();
	
	@Delete("delete from wxuser_job where id = #{id}")
	public int deleteWxUser_Job(int id);
	
	@Delete("delete from wxuser_job where userid = #{userid}")
	public int deleteWxUser_JobByUserId(int userid);
	
	@Delete("delete from wxuser_job where userid = #{userid} and jobid = #{jobid}")
	public int delResum(@Param("userid")int userid,@Param("jobid")int jobid);
}
