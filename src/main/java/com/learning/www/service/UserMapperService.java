package com.learning.www.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.learning.www.entity.User;

@Service
public interface UserMapperService {
	
	/***
	 * get 得到所有用户的信息
	 * @return
	 */
	public List<User> getUserInfo();
	
	/***
	 * get 根据id得到用户的信息
	 * @param id
	 * @return
	 */
	public User getUserdById(int id);
	
	/***
	 * get 根据username得到password
	 * @param username
	 * @return
	 */
	public User getPasswordByUsername(String username);
	
	public String getUserNameById(int id);
	
	/***
	 * post 新增用户
	 * @param user
	 * @return
	 */
	public int postUserInfo(User user);
	
	/***
	 * del 删除用户信息
	 * @param id
	 * @return
	 */
	public int deleteUserInfo(int id);
	
	/***
	 * put 更新用户信息
	 * @param user
	 * @return
	 */
	public int putUserInfoById(User user);
	
	/***
	 * put 重置pasword
	 * @param user
	 * @return
	 */
	public int putUserPasswordById(@Param("id")int id,@Param("password")String password);
	
	public int deleteUserByUsername(String username);
	
}
