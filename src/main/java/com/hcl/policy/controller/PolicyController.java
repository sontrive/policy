package com.hcl.policy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.PolicyService;

@CrossOrigin
@RestController
public class PolicyController {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);
	
	@Autowired
	PolicyService policyService;
	
	@GetMapping("/policies")
	public ResponseEntity<Object> getAllPolicies(){
		return new ResponseEntity<>(policyService.getAllPolicies(), HttpStatus.OK);
	}
	
	@GetMapping("/policy/{policyId}")
	public ResponseEntity<Object> getDetailsOfPolicy(@PathVariable Long policyId) throws ApplicationException{
		
              logger.info("Received user id");
		
		if (null == policyId) {
			throw new ApplicationException("Please enter valid user Id...");
		}
		else {
			logger.debug("Policy Id received is  "+ policyId);
			ResponseDTO policies= policyService.getPolicyDetails(policyId);
			logger.debug("Policy details are " + policies );
			return new ResponseEntity<>(policies,HttpStatus.OK);
		}
		
	}

}
