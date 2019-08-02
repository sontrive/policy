package com.hcl.policy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.policy.controller.PolicyController;
import com.hcl.policy.dto.PolicyTrendAnalysisDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.repository.PolicyRepository;
import com.hcl.policy.repository.UserPolicyDetailsRepository;
import com.hcl.policy.service.TrendAnalysisService;

/**
 * @author Administrator
 *
 */
@Service
public class TrendAnalysisServiceImpl implements TrendAnalysisService {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	UserPolicyDetailsRepository userPolicyDetailsRepository;
	
	@Autowired
	PolicyRepository policyRepository;

	/**
	 * @param criteria
	 * @return object of ResponseDTO
	 * @throws ApplicationException
	 */
	public ResponseDTO getPolicyTrendAnalysis(String criteria) throws ApplicationException {

		logger.info("Inside getPolicyTrendAnalysis method in TrendAnalysisServiceImpl");
		Integer analysisDuration = 0;
		List<PolicyTrendAnalysisDTO> policiesTrendOutputList = new ArrayList<>();
		List<Long> unoptedPolicyList = new ArrayList<>();
		List<List<?>> policiesTrendList = null;
		if ("w".equalsIgnoreCase(criteria)) {
			analysisDuration = 7;
			policiesTrendList = userPolicyDetailsRepository
					.getAnalysisDurationBasedPolicyTrendAnalysis(analysisDuration);
			unoptedPolicyList = userPolicyDetailsRepository.getAllUnOptedPolicies(analysisDuration);
		} else if ("m".equalsIgnoreCase(criteria)) {
			analysisDuration = 30;
			policiesTrendList = userPolicyDetailsRepository
					.getAnalysisDurationBasedPolicyTrendAnalysis(analysisDuration);
			unoptedPolicyList = userPolicyDetailsRepository.getAllUnOptedPolicies(analysisDuration);
		} else if ("d".equalsIgnoreCase(criteria)) {
			analysisDuration = 3000;
			policiesTrendList = userPolicyDetailsRepository.getAllPolicyTrendAnalysis();
			unoptedPolicyList = userPolicyDetailsRepository.getAllUnOptedPolicies(analysisDuration);
		}
		if (null != policiesTrendList && !policiesTrendList.isEmpty()) {
			int totalPoliciesOpted = 0;
			for (List<?> policyTrendAnalysis : policiesTrendList) {
				totalPoliciesOpted = totalPoliciesOpted + Integer.parseInt(String.valueOf(policyTrendAnalysis.get(1)));
			}
			if (0 < totalPoliciesOpted) {
				for (List<?> policyTrendAnalysis : policiesTrendList) {
					Long policyId = Long.parseLong(String.valueOf(policyTrendAnalysis.get(0)));
					PolicyTrendAnalysisDTO policyTrendAnalysisDTO = new PolicyTrendAnalysisDTO();
					policyTrendAnalysisDTO.setPolicyId(policyRepository.getPolicyDetails(policyId));
					policyTrendAnalysisDTO.setPolicyOptedCount(Long.parseLong(String.valueOf(policyTrendAnalysis.get(1))));
					policyTrendAnalysisDTO.setTrendPercent(
							(float) (Integer.parseInt(String.valueOf(policyTrendAnalysis.get(1)))) / (totalPoliciesOpted) * 100);
					policiesTrendOutputList.add(policyTrendAnalysisDTO);
				}
			}
		}
		
		getUnOptedPoliciesList(policiesTrendOutputList, unoptedPolicyList);
		
		if(policiesTrendOutputList.isEmpty()) {
			throw new ApplicationException("Please enter valid analysis criteria.");
		}
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setData(policiesTrendOutputList);
		responseDTO.setHttpStatus(HttpStatus.CREATED);
		responseDTO.setMessage("Policy trend has been generated");
		return responseDTO;

	}
	
	private void getUnOptedPoliciesList(List<PolicyTrendAnalysisDTO> policiesTrendOutputList,List<Long> unoptedPolicyList){
		
		if(!unoptedPolicyList.isEmpty()) {
			for(Long policyId : unoptedPolicyList) {
				PolicyTrendAnalysisDTO policyTrendAnalysisDTO = new PolicyTrendAnalysisDTO();
				policyTrendAnalysisDTO.setPolicyId(policyRepository.getPolicyDetails(policyId));
				policyTrendAnalysisDTO.setPolicyOptedCount(0L);
				policyTrendAnalysisDTO.setTrendPercent(0.0f);
				policiesTrendOutputList.add(policyTrendAnalysisDTO);
			}
		}
	}
}
