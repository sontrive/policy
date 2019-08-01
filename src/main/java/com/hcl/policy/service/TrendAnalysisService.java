package com.hcl.policy.service;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;

public interface TrendAnalysisService {

	public ResponseDTO getPolicyTrendAnalysis(String criteria) throws ApplicationException;
}
