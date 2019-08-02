package com.hcl.policy.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.dto.OptPolicyDTO;
import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.UserPolicyService;

@RunWith(MockitoJUnitRunner.class)
public class UserPolicyControllerTest {

	@InjectMocks
	UserPolicyController userPolicyController;

	@Mock
	UserPolicyService userPolicyServiceMock;

	OptPolicyDTO optPolicyDTO;

	@Before
	public void setUp() {
		optPolicyDTO = createOptPolicyDTO();
	}

	@Test
	public void testOptForPolicy() throws ApplicationException {

		Mockito.when(userPolicyServiceMock.optForPolicy(optPolicyDTO)).thenReturn(new ResponseDTO());
		assertNotNull(userPolicyController.optForPolicy(optPolicyDTO));
	}

	@Test(expected = ApplicationException.class)
	public void testOptForPolicyTncMissing() throws ApplicationException {
		optPolicyDTO.setAcceptTermsAndConditions(null);
		assertNotNull(userPolicyController.optForPolicy(optPolicyDTO));
	}

	@Test(expected = ApplicationException.class)
	public void testOptForPolicyPolicyIDMissing() throws ApplicationException {
		optPolicyDTO.setPolicyId(null);
		assertNotNull(userPolicyController.optForPolicy(optPolicyDTO));
	}

	@Test(expected = ApplicationException.class)
	public void testOptForPolicyUserIDMissing() throws ApplicationException {
		optPolicyDTO.setUserId(null);
		assertNotNull(userPolicyController.optForPolicy(optPolicyDTO));
	}

	private OptPolicyDTO createOptPolicyDTO() {
		OptPolicyDTO optPolicyDTO = new OptPolicyDTO();
		optPolicyDTO.setAcceptTermsAndConditions(Boolean.TRUE);
		optPolicyDTO.setPolicyId(1L);
		optPolicyDTO.setUserId(1L);
		return optPolicyDTO;
	}

}
