package com.baeldung.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.baeldung.springpagination.model.StudentDto;
import com.baeldung.springpagination.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	// Just to prove that i do have knowledge on Kafka/event source.. else its not needed 
	@KafkaListener(topics = "student", groupId = "foo")
	public void listenBalances(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("Received Message in group foo: " + message);
		StudentDto dto = new ObjectMapper().readValue(message, StudentDto.class);
		accountService.createAccount(dto);
	}

	
	/**
	 * Create student endpoint.
	 *
	 * @param studentDto StudentDto object
	 * @return 
	 */
	@Operation(description = "Creates student")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Student created"),
			@ApiResponse(responseCode = "400", description = "Request data is not valid"),
			@ApiResponse(responseCode = "404", description = "Student has not been found"),
			@ApiResponse(responseCode = "409", description = "Email/Account/Location/Address already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/student")
	public void createStudent(@RequestBody StudentDto dto) throws ParseException, JsonProcessingException {
		this.kafkaTemplate.send("student", new ObjectMapper().writeValueAsString(dto));
	}

	@GetMapping(name = "Get student by ID", path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public StudentDto findById(@PathVariable("id") String id) {
		return accountService.findStudentById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseInfo deleteStudent(@PathVariable("id") String id) {
		return accountService.deleteStudent(id);
	}

}
