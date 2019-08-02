package com.hcl.policy.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.dto.PolicyTrendAnalysisDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.repository.PolicyRepository;
import com.hcl.policy.repository.UserPolicyDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class TrendAnalysisServiceImplTest {

	@InjectMocks
	TrendAnalysisServiceImpl trendAnalysisServiceImpl;

	@Mock
	UserPolicyDetailsRepository userPolicyDetailsRepository;
	
	@Mock
	PolicyRepository policyRepository;

	Integer analysisDuration = 0;
	List<PolicyTrendAnalysisDTO> policiesTrendOutputList;
	List<Long> unoptedPolicyList;
	List<List<?>> policiesTrendList;
	PolicyTrendAnalysisDTO policyTrendAnalysisDTO;

	@Before
	public void setUp() {
		policiesTrendOutputList = new ArrayList<>();
		unoptedPolicyList = new ArrayList<>();
		policiesTrendList = new ArrayList<>();
		policyTrendAnalysisDTO = new PolicyTrendAnalysisDTO();
	}

	@Test
	public void testGetPolicyTrendAnalysisIfWeeklyTrendIsRequired() throws ApplicationException {

		unoptedPolicyList.add(1L);
		when(userPolicyDetailsRepository.getAllUnOptedPolicies(7)).thenReturn(unoptedPolicyList);
		policiesTrendOutputList.add(policyTrendAnalysisDTO);
		assertNotNull(trendAnalysisServiceImpl.getPolicyTrendAnalysis("w"));
	}

	@Test
	public void testGetPolicyTrendAnalysisIfMonthlyTrendIsRequired() throws ApplicationException {

		unoptedPolicyList.add(1L);
		when(userPolicyDetailsRepository.getAllUnOptedPolicies(30)).thenReturn(unoptedPolicyList);
		policiesTrendOutputList.add(policyTrendAnalysisDTO);
		assertNotNull(trendAnalysisServiceImpl.getPolicyTrendAnalysis("m"));
	}

	@Test
	public void testGetPolicyTrendAnalysisIfDefaultTrendIsRequired() throws ApplicationException {

		unoptedPolicyList.add(1L);
		when(userPolicyDetailsRepository.getAllUnOptedPolicies(3000)).thenReturn(unoptedPolicyList);
		policiesTrendOutputList.add(policyTrendAnalysisDTO);
		assertNotNull(trendAnalysisServiceImpl.getPolicyTrendAnalysis("d"));
	}

	@Test(expected = ApplicationException.class)
	public void testGetPolicyTrendAnalysisIfrendIsIncorrect() throws ApplicationException {

		assertNull(trendAnalysisServiceImpl.getPolicyTrendAnalysis("k"));
	}

}
