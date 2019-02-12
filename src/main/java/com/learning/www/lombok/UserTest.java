package com.learning.www.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTest {
	
	private String username;
	private int id;
	private int age;
	
}
