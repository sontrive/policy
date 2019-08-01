package com.hcl.policy.service;

import com.hcl.policy.dto.ResponseDTO;

public interface PolicyService {
	
	public ResponseDTO getAllPolicies();

	public ResponseDTO getPolicyDetails(Long userId);

}
