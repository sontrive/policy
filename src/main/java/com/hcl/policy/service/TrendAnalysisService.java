package com.hcl.policy.service;

import com.hcl.policy.dto.ResponseDTO;

public interface TrendAnalysisService {

	public ResponseDTO getPolicyTrendAnalysis(String criteria);
}
