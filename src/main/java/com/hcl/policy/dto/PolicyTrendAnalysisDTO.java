package com.hcl.policy.dto;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class PolicyTrendAnalysisDTO {

	private Long policyId;
	
	private Long policyOptedCount;
	
	private Float trendPercent;
}
