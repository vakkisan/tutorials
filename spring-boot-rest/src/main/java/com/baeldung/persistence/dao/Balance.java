package com.baeldung.persistence.dao;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
	@Id
	private String accountNumber;

	private Calendar lastUpdateTimestamp;

	private Double balance;

}
