package com.hcl.policy.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.policy.dto.PolicyResponseDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.entity.Policy;
import com.hcl.policy.repository.PolicyRepository;
import com.hcl.policy.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);
	
	@Autowired
	PolicyRepository policyRepository;
	
	public ResponseDTO getAllPolicies() {
		
		ResponseDTO responseDTO = new ResponseDTO();
		List<Policy> policyList = policyRepository.findAll();
		if (null != policyList && !policyList.isEmpty())  {
			List<PolicyResponseDTO> policyResponseDTOList = policyList.stream()
					.map(i -> new PolicyResponseDTO(i.getId(), i.getName(), i.getEntryAge(), i.getMaxMaturityAge(), i.getPolicyTerm(), i.getMinPremium(), i.getMinSumAssured(), i.getStatus()))
					.collect(Collectors.toList());
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("List of all policies");
			responseDTO.setData(policyResponseDTOList);
		} else {
			logger.info("Policy not present");
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("No policy found.");
		}
		return responseDTO;
		
	}

}
