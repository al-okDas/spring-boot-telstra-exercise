package com.telstra.codechallenge.oldestuseraccountwithzerofollower;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OldestUserAccountsControllerTest {

	@Mock
	public OldestUserAccountService oldestUserAccountService;
	
	@InjectMocks
	private OldestUserAccountsController userAccountController;
	
	@Test
	public void getOldestAccountsTest1() throws Exception {
		UserAccountsList userAccountsList = Mockito.mock(UserAccountsList.class);
		List<UserAccounts> listItems = new ArrayList<>();
		UserAccounts account1 = Mockito.mock(UserAccounts.class);
		account1.setId(String.valueOf(113));
		account1.setLogin("mattetti");
		account1.setHtml_url("https://github.com/mattetti");

		UserAccounts account2 = Mockito.mock(UserAccounts.class);
		account2.setId(String.valueOf(150));
		account2.setLogin("sevenwire");
		account2.setHtml_url("https://github.com/sevenwire");

		UserAccounts account3 = Mockito.mock(UserAccounts.class);
		account3.setId(String.valueOf(167));
		account3.setLogin("entryway");
		account3.setHtml_url("https://github.com/entryway");

		listItems.add(account1);
		listItems.add(account2);
		listItems.add(account3);
		userAccountsList.setItems(listItems);
		
		Mockito.when(oldestUserAccountService.getOldestAccounts()).thenReturn(userAccountsList);
		Mockito.when(userAccountsList.getItems()).thenReturn(listItems);
		Integer noOfAccounts=4;
		userAccountController.getOldestAccounts(noOfAccounts);
	}
	
	@Test
	public void getOldestAccountsTest2() throws HttpClientErrorException, Exception {
		ResponseEntity<Object> responseEntity = Mockito.mock(ResponseEntity.class);
		Mockito.when(oldestUserAccountService.getOldestAccounts())
				.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "test http not found"));
		Integer noOfAccounts=4;
		userAccountController.getOldestAccounts(noOfAccounts);
	}

	@Test
	public void getOldestAccountsTest3() throws RestClientException, Exception {
		ResponseEntity<Object> responseEntity = Mockito.mock(ResponseEntity.class);
		Mockito.when(oldestUserAccountService.getOldestAccounts())
				.thenThrow(new RestClientException("test restclient exception"));
		Integer noOfAccounts=4;
		userAccountController.getOldestAccounts(noOfAccounts);
	}

	@Test
	public void getOldestAccountsTest4() throws Exception {
		ResponseEntity<Object> responseEntity = Mockito.mock(ResponseEntity.class);
		Mockito.doAnswer(i -> { throw new Exception("test general exception"); })
	    .when(oldestUserAccountService)
	    .getOldestAccounts();
		
		Integer noOfAccounts=4;
		userAccountController.getOldestAccounts(noOfAccounts);
	}
}
