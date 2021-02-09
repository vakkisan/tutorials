package com.baeldung.persistence.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.baeldung.springpagination.model.AddressDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	String studentName;

	AddressDto addressDto;

	String phoneNumber;

}
