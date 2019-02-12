package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchartJobAmount {
	private int jobid;
	private String jobname;
	private String cname;
	private int amount;
}
