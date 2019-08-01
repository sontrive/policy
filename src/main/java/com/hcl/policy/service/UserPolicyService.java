package com.hcl.policy.service;

import org.springframework.core.io.InputStreamResource;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;

public interface UserPolicyService {
	
	public ResponseDTO optForPolicy(OptPolicyDTO optPolicyDTO) throws ApplicationException;

	public InputStreamResource getPolicyDetails(Long userId) throws ApplicationException;

}
