package com.hcl.policy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.dto.PolicyTrendAnalysisDTO;
import com.hcl.policy.repository.UserPolicyDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class TrendAnalysisServiceImplTest {

	@InjectMocks
	TrendAnalysisServiceImpl trendAnalysisServiceImpl;
	
	@Mock
	UserPolicyDetailsRepository userPolicyDetailsRepository;
	
	Integer analysisDuration = 0;
	List<PolicyTrendAnalysisDTO> policiesTrendOutputList;
	List<Long> unoptedPolicyList;
	List<List<?>> policiesTrendList;
	
	@Before
	public void setUp() {
		policiesTrendOutputList = new ArrayList<>();
		 unoptedPolicyList = new ArrayList<>();
		 policiesTrendList = new ArrayList<>();
	}

}
