package com.hcl.policy.service;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;

/**
 * @author Administrator
 *
 */
public interface TrendAnalysisService {

	/**
	 * @param criteria
	 * @return object of ResponseDTO
	 * @throws ApplicationException
	 */
	public ResponseDTO getPolicyTrendAnalysis(String criteria) throws ApplicationException;
}
