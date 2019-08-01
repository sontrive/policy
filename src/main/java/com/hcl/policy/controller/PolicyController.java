package com.hcl.policy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PolicyController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	private static final String ERROR_MSG = "Mandetory Element missing : ";

}
