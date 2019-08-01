package com.hcl.policy.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.TrendAnalysisService;

@RunWith(MockitoJUnitRunner.class)
public class TrendAnalysisControllerTest {

	@InjectMocks
	TrendAnalysisController trendAnalysisController;
	
	@Mock
	TrendAnalysisService trendAnalysisService;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetTrendAnalysisIfCorrectCriteriaIsProvided() throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();
		String criteria = "w";
		when(trendAnalysisService.getPolicyTrendAnalysis(criteria)).thenReturn(responseDTO);
		assertNotNull(trendAnalysisController.getTrendAnalysis(criteria));
	}
	
	@Test(expected = ApplicationException.class)
	public void testGetTrendAnalysisIfCriteriaIsEmpty() throws ApplicationException {
		String criteria = "";
		assertNull(trendAnalysisController.getTrendAnalysis(criteria));
	}
}
