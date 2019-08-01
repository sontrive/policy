package com.hcl.policy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.policy.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long userId);

}
