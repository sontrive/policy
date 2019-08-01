package com.hcl.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.UserPolicyDetails;

@Repository
public interface UserPolicyDetailsRepository extends JpaRepository<UserPolicyDetails, Long> {

	List<UserPolicyDetails> findAllById(Long userId);

}
