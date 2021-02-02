package com.baeldung.persistence.dao;

import java.util.Calendar;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends EntityWithUUID {
	
	private String accountNumber;

	private Calendar transactionTs;

	private String type;

	private Double amount;

}
