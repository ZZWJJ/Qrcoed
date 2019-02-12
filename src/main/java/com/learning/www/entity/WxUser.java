package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxUser {
	private int id;
	private int sex;
	private int jobid;
	private String username;
	private String age;
	private String telphone;
	private String home;
	private String school;
	private String job;
	private String password;
	private String jobname;
}
