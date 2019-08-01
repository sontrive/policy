package com.hcl.policy.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.dto.PolicyDetailsDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.PolicyService;

@RunWith(MockitoJUnitRunner.class)
public class PolicyControlerTest {
	

		@InjectMocks
		PolicyController policyController;
		
		@Mock
		PolicyService policyService;
		
		PolicyDetailsDTO policy;
		
		@Before
		public void setUp() {
			 policy=new PolicyDetailsDTO();
		}
		
		@Test
		public void testGetAllCoursesIfPoliciesArePresent() {
			
			assertNotNull(policyController.getAllPolicies());
		}
		
		@Test
		public void testGetPolicyIfPolicyIdIsCorrect() throws ApplicationException {
			policy.setId(1L);
			policy.setName("LIC Jeevan Saral");
			assertNotNull(policyController.getDetailsOfPolicy(1L));
		}

}
