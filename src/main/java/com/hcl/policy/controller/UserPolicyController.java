package com.hcl.policy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.UserPolicyService;

@RestController
@RequestMapping("/policy")
public class UserPolicyController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserPolicyController.class);
	
	@Autowired
	UserPolicyService userPolicyService;

	@PostMapping("")
	public ResponseEntity<Object> optForPolicy(@RequestBody OptPolicyDTO optPolicyDTO) throws ApplicationException{
		logger.info("Received opt for policy request.");
		return new ResponseEntity<>(userPolicyService.optForPolicy(optPolicyDTO), HttpStatus.OK);
	}
}
