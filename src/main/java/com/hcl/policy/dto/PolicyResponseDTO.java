package com.hcl.policy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponseDTO {

	private Long policyId;

	private String policyName;

	private Integer entryAge;

	private Integer maxMaturityAge;

	private Integer policyTerm;

	private Double minPremium;

	private Double minSumAssured;

	private String status;

}
