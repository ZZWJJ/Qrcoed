package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int id;
	private String username;
	private String phone;
	private String password;
	private String salt;
	private String role;
	private int type;
}
