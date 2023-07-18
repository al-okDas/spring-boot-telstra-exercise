package com.telstra.codechallenge.oldestuseraccountwithzerofollower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OldestUserAccountService {
	
	@Value("${userAccountApi.path}")
	private String userAccountApiPath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public UserAccountsList getOldestAccounts() {
		ResponseEntity<UserAccountsList> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(userAccountApiPath,UserAccountsList.class);
		} catch (Exception ex) {
			throw ex;
		}
		return responseEntity.getBody();
	}
}
