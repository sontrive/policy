package com.hcl.policy.serviceimpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.PolicyResponseDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.entity.Policy;
import com.hcl.policy.entity.User;
import com.hcl.policy.entity.UserPolicyDetails;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.repository.PolicyRepository;
import com.hcl.policy.repository.UserPolicyDetailsRepository;
import com.hcl.policy.repository.UserRepository;
import com.hcl.policy.service.UserPolicyService;

@Service
public class UserPolicyServiceImpl implements UserPolicyService {

	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserPolicyDetailsRepository userPolicyDetailsRepository;

	private static final String USER_NOT_ELIGIBLE_ERR_MSG = "User is not eligible for policy. ";

	@Override
	public ResponseDTO optForPolicy(OptPolicyDTO optPolicyDTO) throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();

		if (BooleanUtils.isFalse(optPolicyDTO.getAcceptTermsAndConditions())) {
			throw new ApplicationException("To opt for policy user should accept the terms and conditions.");
		}

		Optional<User> optionalUser = userRepository.findById(optPolicyDTO.getUserId());
		User user;

		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		} else {
			throw new ApplicationException("Invalid User Id");
		}

		Optional<Policy> optionalPolicy = policyRepository.findById(optPolicyDTO.getPolicyId());
		Policy policy;

		if (optionalPolicy.isPresent()) {
			policy = optionalPolicy.get();
		} else {
			throw new ApplicationException("Invalid policy Id");
		}

		Period diff = Period.between(user.getDob(), LocalDate.now());
		if (diff.getYears() < policy.getEntryAge()) {
			throw new ApplicationException(
					USER_NOT_ELIGIBLE_ERR_MSG + "Age should be greater than " + policy.getEntryAge());
		}

		UserPolicyDetails userPolicyDetails = new UserPolicyDetails();
		userPolicyDetails.setOptedDate(LocalDate.now());
		userPolicyDetails.setPolicyId(policy);
		userPolicyDetails.setUserId(user);
		userPolicyDetails.setStatus(1);
		UserPolicyDetails savedUserPolicyDetails = userPolicyDetailsRepository.save(userPolicyDetails);
	
		Policy savedPolicy = savedUserPolicyDetails.getPolicyId();
		PolicyResponseDTO policyResponseDTO = createResponse(savedPolicy);
		
		responseDTO.setHttpStatus(HttpStatus.OK);
		responseDTO.setMessage("You have successfully opt for policy. Find below details:");
		responseDTO.setData(policyResponseDTO);
		return responseDTO;
	}
	
	private PolicyResponseDTO createResponse(Policy savedPolicy) {
		PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
		policyResponseDTO.setEntryAge(savedPolicy.getEntryAge());
		policyResponseDTO.setMaxMaturityAge(savedPolicy.getMaxMaturityAge());
		policyResponseDTO.setMinPremium(savedPolicy.getMinPremium());
		policyResponseDTO.setMinSumAssured(savedPolicy.getMinPremium());
		policyResponseDTO.setPolicyName(savedPolicy.getName());
		policyResponseDTO.setPolicyId(savedPolicy.getId());
		policyResponseDTO.setPolicyTerm(savedPolicy.getPolicyTerm());
		 return policyResponseDTO;
	}

}
