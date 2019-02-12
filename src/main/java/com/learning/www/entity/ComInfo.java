package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComInfo {
	private int id;
	private int uid;
	private String uname;
	private String cname;
	private String contactor;
	private String phone;
	private String addinfo;
	
}
