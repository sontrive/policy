package com.hcl.policy.serviceimpl;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.ResponseDTO;
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

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	UserPolicyDetailsRepository userPolicyDetailsRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseDTO optForPolicy(OptPolicyDTO optPolicyDTO) throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<User> OptionalUser = userRepository.findById(optPolicyDTO.getUserId());
		
		
		
		
		return responseDTO;
	}

	@Override
	public ResponseEntity<InputStreamResource> getPolicyDetails(Long userId) {
		List<UserPolicyDetails> result = userPolicyDetailsRepository.findAllById(userId);
		
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
	
}
