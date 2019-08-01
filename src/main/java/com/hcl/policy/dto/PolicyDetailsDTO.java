package com.hcl.policy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDetailsDTO {

	private Long id;

	private String name;

	private Integer entryAge;

	private Integer maxMaturityAge;

	private Integer policyTerm;

	private Double minPremium;

	private Double minSumAssured;

	private String modeOfPayment;

	private String taxBenefit;

	private String loanFacility;

	private String policyDescription;

	private String status;

	private String termsAndConditions;

}
