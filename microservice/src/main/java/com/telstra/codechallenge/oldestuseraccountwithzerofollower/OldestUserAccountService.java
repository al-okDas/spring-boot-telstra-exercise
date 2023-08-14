package com.telstra.codechallenge.oldestuseraccountwithzerofollower;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OldestUserAccountService {
	
	@Value("${userAccountApi.path}")
	private String userAccountApiPath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public UserAccountsList getOldestAccounts() throws Exception {
		ResponseEntity<UserAccountsList> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(userAccountApiPath, UserAccountsList.class);
		} catch (HttpClientErrorException ex) {
			throw ex;
		} catch (RestClientException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
		return responseEntity.getBody();
	}

}
