package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZphInfo {
	private int id;
	private String title;
	private String time;
	private String place;
	private String add;
	private int state;
	
}
