package com.telstra.codechallenge.oldestuseraccountwithzerofollower;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@RestController
public class OldestUserAccountsController {
	
	@Autowired
	public OldestUserAccountService oldestUserAccountService;
	
	@GetMapping(path = "/getOldestAccounts")
	  public ResponseEntity<?> getOldestAccounts(@RequestParam Integer noOfAccounts) {
		try {
			List<UserAccounts> accountList=oldestUserAccountService.getOldestAccounts().getItems();
			accountList=accountList.stream().limit(noOfAccounts).collect(Collectors.toList());
			return ResponseEntity.ok(accountList);
		}catch (HttpClientErrorException ex) {
			ErrorResource err=new ErrorResource();
			err.setCode(ex.getStatusCode().toString());
			err.setMessage(ex.getStatusText());
			return new ResponseEntity<>(err,ex.getStatusCode());
		} catch (RestClientException ex) {
			ErrorResource err=new ErrorResource();
			err.setCode("500");
			err.setMessage(ex.getMessage());
			return ResponseEntity.internalServerError().body(err);
		}catch(Exception ex) {
			ErrorResource err=new ErrorResource();
			err.setCode("500");
			err.setMessage(ex.getMessage());
			return ResponseEntity.internalServerError().body(err);
		}
		
	  }

}
