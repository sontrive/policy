package com.hcl.policy.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.repository.PolicyRepository;
import com.hcl.policy.repository.UserRepository;
import com.hcl.policy.service.UserPolicyService;

@Service
public class UserPolicyServiceImpl implements UserPolicyService {

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseDTO optForPolicy(OptPolicyDTO optPolicyDTO) throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();
		
		return responseDTO;
	}
	
}
