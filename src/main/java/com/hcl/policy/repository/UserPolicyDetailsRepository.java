package com.hcl.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.UserPolicyDetails;

@Repository
public interface UserPolicyDetailsRepository extends JpaRepository<UserPolicyDetails, Long> {

}
