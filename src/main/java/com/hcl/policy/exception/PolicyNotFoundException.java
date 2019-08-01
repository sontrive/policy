package com.hcl.policy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PolicyNotFoundException extends RuntimeException {
	    
		private static final long serialVersionUID = 1L;
		
	    public PolicyNotFoundException() {
	        super();
	    }
	    public PolicyNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public PolicyNotFoundException(String message) {
	        super(message);
	    }
	    public PolicyNotFoundException(Throwable cause) {
	        super(cause);
	    }

}
