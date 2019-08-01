package com.hcl.policy;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyApplicationTests {

	@Test
	public void contextLoads() {
		String hello = "Hello";
		assertSame("Hello", hello );
	}

}
