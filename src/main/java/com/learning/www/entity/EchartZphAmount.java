package com.learning.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchartZphAmount {
	private int amount;
	private String place;
	private String time;
}
