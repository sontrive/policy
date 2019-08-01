package com.hcl.policy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.exception.ApplicationException;
import com.hcl.policy.service.TrendAnalysisService;

/**
 * @author Administrator
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/policies/trend")
public class TrendAnalysisController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	TrendAnalysisService trendAnalysisService;

	/**
	 * @param criteria
	 * @return object of ResponseEntity
	 * @throws ApplicationException
	 */
	@GetMapping("")
	public ResponseEntity<Object> getTrendAnalysis(@RequestParam("criteria") String criteria) throws ApplicationException {
		
		logger.info("Inside getTrendAnalysis method in TrendAnalysisController");
		ResponseDTO responseDTO = null;
		if (StringUtils.isEmpty(criteria)) {
			throw new ApplicationException("Please enter analysis criteria");
		}
		responseDTO = trendAnalysisService.getPolicyTrendAnalysis(criteria);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}
}
