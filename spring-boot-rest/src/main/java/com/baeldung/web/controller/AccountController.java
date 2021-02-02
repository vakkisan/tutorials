package com.baeldung.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.persistence.dao.Transaction;
import com.baeldung.springpagination.model.BalanceDto;
import com.baeldung.springpagination.model.TransactionDto;
import com.baeldung.springpagination.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "balances", groupId = "foo")
	public void listenBalances(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("Received Message in group foo: " + message);
		BalanceDto balanceDto = new ObjectMapper().readValue(message, BalanceDto.class);
		accountService.createAccount(balanceDto);
	}

	@KafkaListener(topics = "transactions", groupId = "foo")
	public void listenTransactions(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("Received Message in group foo: " + message);
		TransactionDto transactions = new ObjectMapper().readValue(message, TransactionDto.class);
		accountService.postTransactions(transactions);
	}

	@PostMapping("/balances")
	public void balances(@RequestBody BalanceDto balanceDto) throws ParseException, JsonProcessingException {
		this.kafkaTemplate.send("balances", new ObjectMapper().writeValueAsString(balanceDto));
	}

	@PostMapping("/transactions")
	public void transactions(@RequestBody TransactionDto transactionsDto)
			throws ParseException, JsonProcessingException {
		this.kafkaTemplate.send("transactions", new ObjectMapper().writeValueAsString(transactionsDto));
	}

	@GetMapping(name = "Get balance by ID", path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public BalanceDto findById(@PathVariable("id") String id) {
		return accountService.findBalanceById(id);
	}

	@GetMapping(name = "Get balances", path = "/{id}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public Set<Transaction> findById(@PathVariable("id") String id, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @PathVariable(name = "type") String type) {
		return accountService.findBalances(id, fromDate, toDate, type);
	}

	/*
	 * @GetMapping(name = "Get All", produces = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseStatus(org.springframework.http.HttpStatus.OK) public
	 * BusinessAccountPageDto findAllParents(@RequestParam(value = "page",
	 * defaultValue = "0") Integer page,
	 * 
	 * @RequestParam(value = "size", defaultValue = "30") Integer size) { return
	 * dataFillerService.findAllTransactions(page, size); }
	 */
}
