package com.hcl.policy.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.policy.dto.PolicyDetailsDTO;
import com.hcl.policy.dto.PolicyResponseDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.entity.Policy;
import com.hcl.policy.exception.PolicyNotFoundException;
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
		if (!policyList.isEmpty()) {
			List<PolicyResponseDTO> policyResponseDTOList = policyList.stream()
					.map(i -> new PolicyResponseDTO(i.getId(), i.getName(), i.getEntryAge(), i.getMaxMaturityAge(),
							i.getPolicyTerm(), i.getMinPremium(), i.getMinSumAssured(), i.getStatus()))
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

	public ResponseDTO getPolicyDetails(Long policyId) throws PolicyNotFoundException {

		logger.info("Inside Service method");
		Optional<Policy> optPolicy = policyRepository.findById(policyId);

		if (optPolicy.isPresent()) {
			Policy policy = optPolicy.get();
			Policy policyDetails = policyRepository.getPolicyDetails(policy.getId());
			
			PolicyDetailsDTO resPolicy= new PolicyDetailsDTO();
		   resPolicy.setId(policyDetails.getId());
		   resPolicy.setName(policyDetails.getName());
		   resPolicy.setEntryAge(policyDetails.getEntryAge());
		   resPolicy.setLoanFacility(policyDetails.getLoanFacility());
		   resPolicy.setMaxMaturityAge(policyDetails.getMaxMaturityAge());
		   resPolicy.setMinPremium(policyDetails.getMinPremium());
		   resPolicy.setMinSumAssured(policyDetails.getMinSumAssured());
		   resPolicy.setModeOfPayment(policyDetails.getModeOfPayment());
		   resPolicy.setPolicyDescription(policyDetails.getPolicyDescription());
		   resPolicy.setPolicyTerm(policyDetails.getPolicyTerm());
		   resPolicy.setStatus(policyDetails.getStatus());
		   resPolicy.setTaxBenefit(policyDetails.getTaxBenefit());
		   resPolicy.setTermsAndConditions(policyDetails.getTermsAndConditions());

			ResponseDTO response = new ResponseDTO();
			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("Policy Details for "+ policy.getId()+" are as follows:  ");
			response.setData(resPolicy);
			return response;
		} 
		else {
			throw new PolicyNotFoundException("Invalid policy id");
		}
	}
}
