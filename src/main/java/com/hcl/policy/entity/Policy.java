package com.hcl.policy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "policy")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Policy implements Serializable {

	private static final long serialVersionUID = -8671803081992677318L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private  String name;
	
	@Column(name = "entry_age")
	private Integer entryAge;
	
	@Column(name = "max_maturity_age")
	private Integer maxMaturityAge;
	
	@Column(name = "policy_term")
	private Integer policyTerm;
	
	@Column(name = "min_premium")
	private Double minPremium;
	
	@Column(name = "min_sum_assured")
	private Double minSumAssured;
	
	@Column(name = "mode_of_payment")
	private String modeOfPayment;
	
	@Column(name = "tax_benefit")
	private String taxBenefit;
	
	@Column(name = "loan_facility")
	private String loanFacility;
	
	@Column(name = "policy_description")
	private String policyDescription;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "terms_and_conditions")
	private String termsAndConditions;
	
	@OneToMany(mappedBy = "policyId", cascade = CascadeType.ALL)
	private List<UserPolicyDetails> policyDetailsList = new ArrayList<>();
	
	

}
