package com.telstra.codechallenge.oldestuseraccountwithzerofollower;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		}catch(Exception ex) {
			return ResponseEntity.internalServerError().body(ex.getMessage());
		}
		
	  }

}
