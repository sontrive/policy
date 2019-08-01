package com.hcl.policy.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 4828889558640762268L;

			private String name;
			
			private String category;
				
			private Double price;
		
			private Integer quantity;
			

		}
		

