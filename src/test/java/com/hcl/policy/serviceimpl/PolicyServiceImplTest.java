package com.hcl.policy.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.entity.Policy;
import com.hcl.policy.entity.User;
import com.hcl.policy.entity.UserPolicyDetails;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.repository.PolicyRepository;
import com.hcl.policy.repository.UserPolicyDetailsRepository;
import com.hcl.policy.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceImplTest {

	@InjectMocks
	UserPolicyServiceImpl userPolicyServiceImpl;

	@Mock
	PolicyRepository policyRepositoryMock;

	@Mock
	UserRepository userRepositoryMock;
	
	@Mock
	UserPolicyDetailsRepository userPolicyDetailsRepositoryMock;

	OptPolicyDTO optPolicyDTO;
	User user;
	Policy policy;
	Optional<User> optionalUser;
	Optional<Policy> optionalPolicy;

	@Before
	public void setUp() {
		optPolicyDTO = createOptPolicyDTO();
		user = createUser();
		optionalUser = Optional.of(user);
		policy = createPolicy();
		optionalPolicy = Optional.of(policy);
	}

	@Test
	public void testOptForPolicy() throws ApplicationException {
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(optionalUser);
		Mockito.when(policyRepositoryMock.findById(Mockito.anyLong())).thenReturn(optionalPolicy);
		UserPolicyDetails userPolicyDetails = createUserPolicyDetails() ;
		Mockito.when(userPolicyDetailsRepositoryMock.save(Mockito.any(UserPolicyDetails.class))).thenReturn(userPolicyDetails);
		
		assertNotNull(userPolicyServiceImpl.optForPolicy(optPolicyDTO));

	}

	private OptPolicyDTO createOptPolicyDTO() {
		OptPolicyDTO optPolicyDTO = new OptPolicyDTO();
		optPolicyDTO.setAcceptTermsAndConditions(Boolean.TRUE);
		optPolicyDTO.setPolicyId(1L);
		optPolicyDTO.setUserId(1L);
		return optPolicyDTO;
	}

	private User createUser() {
		User user = new User();
		user.setAddress("Pune");
		user.setDob(LocalDate.of(1992, 05, 22));
		user.setEmail("ssk@gmail.com");
		user.setName("shiv");
		return user;
	}
	
	private Policy createPolicy() {
		Policy policy = new Policy();
		policy.setEntryAge(22);
		policy.setLoanFacility("Yes");
		policy.setMaxMaturityAge(79);
		policy.setMinPremium(10000.0);
		policy.setMinSumAssured(1000.0);
		policy.setModeOfPayment("Anuaal");
		policy.setName("LIC");
		policy.setPolicyDescription("LIC");
		policy.setPolicyTerm(1000);
		policy.setStatus("Active");
		policy.setTaxBenefit("True");
		policy.setTermsAndConditions("termsAndConditions");
		return policy;
	}
	
	private UserPolicyDetails createUserPolicyDetails() {
		UserPolicyDetails userPolicyDetails = new UserPolicyDetails();
		userPolicyDetails.setId(1L);
		userPolicyDetails.setOptedDate(LocalDate.now());
		userPolicyDetails.setPolicyId(createPolicy());
		userPolicyDetails.setUserId(createUser());
		userPolicyDetails.setStatus(1);
		return userPolicyDetails;
	}

}
