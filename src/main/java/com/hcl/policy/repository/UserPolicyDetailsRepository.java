package com.hcl.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.Policy;
import com.hcl.policy.entity.User;
import com.hcl.policy.entity.UserPolicyDetails;

@Repository
public interface UserPolicyDetailsRepository extends JpaRepository<UserPolicyDetails, Long> {

	List<UserPolicyDetails> findByUserId(User user);
 
	List<UserPolicyDetails> findByUserIdAndPolicyId(User user, Policy policy);
}
