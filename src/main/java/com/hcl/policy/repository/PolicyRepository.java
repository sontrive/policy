package com.hcl.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
