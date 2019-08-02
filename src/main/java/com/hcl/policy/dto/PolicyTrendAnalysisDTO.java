package com.hcl.policy.dto;

import com.hcl.policy.entity.Policy;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class PolicyTrendAnalysisDTO {

	private Policy policyId;
	
	private Long policyOptedCount;
	
	private Float trendPercent;
}
