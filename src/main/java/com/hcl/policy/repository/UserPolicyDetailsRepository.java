package com.hcl.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.UserPolicyDetails;

@Repository
public interface UserPolicyDetailsRepository extends JpaRepository<UserPolicyDetails, Long> {

	@Query(value = "select * from user_policy_details where user_id =?1", nativeQuery = true)
	List<UserPolicyDetails> findByUserId(Long userId);

}
