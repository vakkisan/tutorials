package com.baeldung.rest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.springpagination.service.AccountService;

public class AccountServiceTest {
	
    @Autowired
    private AccountService accountService;
    
    //@Spy/@Mock

    @BeforeClass
    public static void initialization() {

        System.setProperty("IafConfigSuffix", "Test");
    }
    
    @Test
    public void whenPostRequest_Inserts() {}
}
