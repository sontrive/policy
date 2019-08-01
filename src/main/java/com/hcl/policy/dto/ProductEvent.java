package com.hcl.policy.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2702573498155806638L;
	private List<ProductDTO> productDTO = new ArrayList<>();
}
