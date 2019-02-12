package com.learning.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.www.lombok.UserTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUser() {
		UserTest user = new UserTest("nihao",15,20);		
		System.out.println(user.toString());
	}
}
