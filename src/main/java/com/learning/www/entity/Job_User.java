package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job_User {
	private int id;
	private int jobid;
	private int userid;
	private String cname;
	private String jobname;
	private String wxusername;
}
