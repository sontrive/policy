package com.hcl.policy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.policy.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	
	Optional<Policy> findById(Long policyId);
	
	@Query(value="select * from policy where id = :policyId", nativeQuery= true)
	Policy getPolicyDetails(Long policyId);

}
