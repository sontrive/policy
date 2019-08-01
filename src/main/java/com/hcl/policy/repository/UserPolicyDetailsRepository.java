package com.hcl.policy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.User;
import com.hcl.policy.entity.UserPolicyDetails;

@Repository
public interface UserPolicyDetailsRepository extends JpaRepository<UserPolicyDetails, Long> {

	List<UserPolicyDetails> findByUserId(Optional<User> optionalUser);

}
