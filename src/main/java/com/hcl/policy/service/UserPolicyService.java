package com.hcl.policy.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;

public interface UserPolicyService {
	
	public ResponseDTO optForPolicy(OptPolicyDTO optPolicyDTO) throws ApplicationException;

	public ResponseEntity<InputStreamResource> getPolicyDetails(Long userId);

}
