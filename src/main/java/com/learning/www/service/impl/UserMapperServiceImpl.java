package com.learning.www.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.www.entity.User;
import com.learning.www.mapper.UserMapper;
import com.learning.www.service.UserMapperService;


@Service
public class UserMapperServiceImpl implements UserMapperService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getPasswordByUsername(String username) {
		return userMapper.getPasswordByUsername(username);
	}

	@Override
	public List<User> getUserInfo() {
		return userMapper.getUserInfo();
	}

	@Override
	public int postUserInfo(User user) {
		return userMapper.postUserInfo(user);
	}

	@Override
	public int deleteUserInfo(int id) {
		return userMapper.deleteUserInfo(id);
	}

	@Override
	public int putUserInfoById(User user) {
		return userMapper.putUserInfoById(user);
	}

	@Override
	public User getUserdById(int id) {
		// TODO Auto-generated method stub
		return userMapper.getUserdById(id);
	}

	@Override
	public int putUserPasswordById(int id, String password) {
		return userMapper.putUserPasswordById(id, password);
	}

	@Override
	public String getUserNameById(int id) {
		return userMapper.getUserNameById(id);
	}

	@Override
	public int deleteUserByUsername(String username) {
		return userMapper.deleteUserByUsername(username);
	}
	
	
}
