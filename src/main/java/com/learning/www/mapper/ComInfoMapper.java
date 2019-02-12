package com.learning.www.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.learning.www.entity.ComInfo;
import com.learning.www.entity.Job_User;

@Mapper
public interface ComInfoMapper {
	
	/***
	 * 获取所有公司信息
	 * @return
	 */
	@Select("select * from company order by id desc")
	public List<ComInfo> getComInfoList();
	/***
	 * 获取所有公司信息:id,cname
	 * @return
	 */
	@Select("select id,cname from company order by id desc")
	public List<ComInfo> getComList();
	
	/***
	 * 根据id获取对应公司信息
	 * @param id
	 * @return
	 */
	@Select("select * from company where id = #{id}")
	public ComInfo getComInfoById(int id);
	
	@Select("select cname from company where id = #{id}")
	public String getComNameInfoById(int id);
	
	@Select("select id from company where cname = #{cname}")
	public int getComidByCname(String cname);
	
	@Select("select Cname from company where cname = #{cname}")
	public String getComByCname(String cname);
	/***
	 * 根据uid获取对应公司信息
	 * @param uid
	 * @return
	 */
	@Select("select * from company where uid = #{uid}")
	public List<ComInfo> getComInfoByUid(int uid);
	
	@Insert("insert into company(uid,uname,cname,contactor,phone,addinfo) values(#{uid},#{uname},#{cname},#{contactor},#{phone},#{addinfo})")
	public int postComInfo(ComInfo cominfo);
	
	@Delete("delete from company where id = #{id}")
	public int deleteComInfo(int id);
	
	@Update("update company set cname=#{cname},contactor=#{contactor},phone=#{phone},addinfo=#{addinfo} where id=#{id}")
	public int putComInfoById(ComInfo cominfo);
	
	@Update("update company set uname=#{uname},uid=#{uid} where id=#{id}")
	public int putUserById(@Param("uid")int uid,@Param("uname")String uname,@Param("id")int id);

	@Select("select userid,jobid from wxuser_job where jobid = any( select id from job where job.comid=#{comid} )")
	public List<Job_User> getResumInfo(int comid);	
}
