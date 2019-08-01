package com.hcl.policy.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.service.PolicyService;

@RunWith(MockitoJUnitRunner.class)
public class PolicyControlerTest {
	

		@InjectMocks
		PolicyController policyController;
		
		@Mock
		PolicyService policyService;
		
		@Before
		public void setUp() {
			
		}
		
		@Test
		public void testGetAllCoursesIfPoliciesArePresent() {
			
			assertNotNull(policyController.getAllPolicies());
		}

}
