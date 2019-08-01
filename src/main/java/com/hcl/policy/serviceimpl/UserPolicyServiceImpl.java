package com.hcl.policy.serviceimpl;

import java.io.ByteArrayInputStream;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;

import java.util.Optional;

import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import com.hcl.policy.controller.UserPolicyController;
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
import com.hcl.policy.util.GeneratePDFReport.GeneratePdfReport;

import lombok.var;

@Service
public class UserPolicyServiceImpl implements UserPolicyService {

	private static final Logger logger = LoggerFactory.getLogger(UserPolicyController.class);
	
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
		
		List<UserPolicyDetails> findByUserIdAndPolicyId = userPolicyDetailsRepository.findByUserIdAndPolicyId(user, policy);
		if(null != findByUserIdAndPolicyId && !findByUserIdAndPolicyId.isEmpty()) {
			throw new ApplicationException("You have already opted this policy.");
		}
		
		Period diff = Period.between(user.getDob(), LocalDate.now());
		if (diff.getYears() < policy.getEntryAge()) {
			throw new ApplicationException(
					USER_NOT_ELIGIBLE_ERR_MSG + "Age should be greater than " + policy.getEntryAge());
		}
		
		if (diff.getYears() < 18) {
			throw new ApplicationException("If user age is less than 18 then atleast one Nominee should be present.");
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
		logger.info("Returning response of opt for policy request.");
		return responseDTO;
	}

	@Override
	public ResponseEntity<InputStreamResource> getPolicyDetails(Long userId) {
		User optionalUser = userRepository.findById(userId).get();
		System.out.println(optionalUser);
		List<UserPolicyDetails> result = userPolicyDetailsRepository.findByUserId(optionalUser);
		
			System.out.println("::::::::::::::::::");
			System.out.println("-->" + result);
		        

		        ByteArrayInputStream bis = GeneratePdfReport.policyReport(result);

		        var headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=PolicyReport.pdf");

		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
		    
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
