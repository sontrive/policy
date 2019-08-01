package com.hcl.policy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.UserPolicyService;

@RestController
@RequestMapping("/policy")
@CrossOrigin
public class UserPolicyController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserPolicyController.class);
	
	private static final String ERROR_MSG = "Mandetory Element missing : ";
	
	@Autowired
	UserPolicyService userPolicyService;

	@PostMapping("")
	public ResponseEntity<Object> optForPolicy(@RequestBody OptPolicyDTO optPolicyDTO) throws ApplicationException{
		logger.info("Received opt for policy request.");
		validateRequest(optPolicyDTO);
		return new ResponseEntity<>(userPolicyService.optForPolicy(optPolicyDTO), HttpStatus.OK);
	}
	
	@GetMapping("/report/{userId}")
	public ResponseEntity<Object> getPolicyDetails(@PathVariable Long userId) {
		logger.info("Received user id for get all policy request.");
		return new ResponseEntity<>(userPolicyService.getPolicyDetails(userId), HttpStatus.OK);
	}
	
	
	
	private void validateRequest(OptPolicyDTO optPolicyDTO) throws ApplicationException{
		if (null != optPolicyDTO.getPolicyId()) {
			throw new ApplicationException(ERROR_MSG + "Policy Id");
		}
		if (null != optPolicyDTO.getUserId()) {
			throw new ApplicationException(ERROR_MSG + "User Id");
		}
		if (null != optPolicyDTO.getAcceptTermsAndConditions()) {
			throw new ApplicationException(ERROR_MSG + "Terms And Conditions");
		}
		
	}
}
