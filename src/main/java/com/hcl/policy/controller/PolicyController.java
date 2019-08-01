package com.hcl.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.policy.entity.User;
import com.hcl.policy.entity.UserPolicyDetails;
import com.hcl.policy.service.PolicyService;

@CrossOrigin
@RestController
public class PolicyController {
	
	@Autowired
	PolicyService policyService;
	
	@GetMapping("/policies")
	public ResponseEntity<Object> getAllPolicies(){
		try
		{
		System.out.println(new ObjectMapper().writeValueAsString(new User()));
		System.out.println(new ObjectMapper().writeValueAsString(new UserPolicyDetails()));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<>(policyService.getAllPolicies(), HttpStatus.OK);
	}

}
