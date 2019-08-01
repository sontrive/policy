package com.hcl.policy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyApplicationTests {

	@Test
	public void contextLoads() {
		String hello = "Hello";
		Assert.assertEquals("Hello", hello);
	}

}
