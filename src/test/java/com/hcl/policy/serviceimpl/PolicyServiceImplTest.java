package com.hcl.policy.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.entity.Policy;
import com.hcl.policy.repository.PolicyRepository;

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceImplTest {
	
	@InjectMocks
	PolicyServiceImpl policyServiceImpl;
	
	@Mock
	PolicyRepository policyRepository;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetAllPoliciesIfPoliciesAreAvailable() {
		List<Policy> policyList = new ArrayList<>();
		Policy policy = new Policy();
		
		policy.setId(1L);
		policy.setName("Lic Jeevan Saral");
		policy.setMinSumAssured(120000.9);
		when(policyRepository.findAll()).thenReturn(policyList);
		assertNotNull(policyServiceImpl.getAllPolicies());
	}

}
