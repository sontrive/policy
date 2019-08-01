package com.hcl.policy.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.repository.UserPolicyDetailsRepository;
import com.hcl.policy.service.TrendAnalysisService;

@Service
public class TrendAnalysisServiceImpl implements TrendAnalysisService {

	@Autowired
	UserPolicyDetailsRepository userPolicyDetailsRepository;
	
	public ResponseDTO getPolicyTrendAnalysis(String criteria) {
		return null;
		
	}
}
