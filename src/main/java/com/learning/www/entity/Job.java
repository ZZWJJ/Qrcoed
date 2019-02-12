package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	private int id;
	private int comid;
	private int num;
	private String jobname;
	private String salary;
	private String addinfo;
}
