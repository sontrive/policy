package com.hcl.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.UserPolicyDetails;

@Repository
public interface UserPolicyDetailsRepository extends JpaRepository<UserPolicyDetails, Long> {

	@Query(value="select policy_id,count(user_id) policy_count from policy.user_policy_details where DATEDIFF(NOW(), opted_date) <= :analysisDuration group by policy_id;",nativeQuery = true)
	public List<List<?>> getAnalysisDurationBasedPolicyTrendAnalysis(Integer analysisDuration);
	
	@Query(value="select policy_id,count(user_id) policy_count from policy.user_policy_details group by policy_id;",nativeQuery = true)
	public List<List<?>> getAllPolicyTrendAnalysis();
	
	@Query(value="SELECT id FROM policy.policy where id  not in (select policy_id policy_count from policy.user_policy_details where DATEDIFF(NOW(), opted_date) <= :analysisDuration group by policy_id);",nativeQuery = true)
	public List<Long> getAllUnOptedPolicies(Integer analysisDuration);
}
