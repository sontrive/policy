package com.hcl.policy.dto;

import lombok.Data;

@Data
public class OptPolicyDTO {
	
	private Long userId;
	
	private Long policyId;
	
	private Boolean acceptTermsAndConditions;

}
