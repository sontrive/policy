package com.hcl.policy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.policy.dto.ResponseDTO;
import com.hcl.policy.service.TrendAnalysisService;

@CrossOrigin
@RestController
@RequestMapping("/policies/trend")
public class TrendAnalysisController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	private static final String ERROR_MSG = "Mandetory Element missing : ";

	@Autowired
	TrendAnalysisService trendAnalysisService;

	@GetMapping("")
	public ResponseEntity<Object> getTrendAnalysis(@RequestParam("criteria") String criteria){
		logger.info("Inside getTrendAnalysis method in TrendAnalysisController");
		ResponseDTO responseDTO = null;
		return new ResponseEntity<Object>(responseDTO, HttpStatus.CREATED);
	}
}
