package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Com_Zph {
	private int id;
	private int zphid;
	private int comid;
	private int isjoin;
	private String addinfo;
	private String zphtitle;
	private String zphtime;
	private int zphstate;
}
