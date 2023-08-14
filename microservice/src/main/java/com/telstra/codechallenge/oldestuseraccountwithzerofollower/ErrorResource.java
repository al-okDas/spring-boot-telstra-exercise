package com.telstra.codechallenge.oldestuseraccountwithzerofollower;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResource {

	private String code;
	private String message;
	
}
