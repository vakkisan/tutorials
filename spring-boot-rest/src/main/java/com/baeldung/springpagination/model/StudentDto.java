package com.baeldung.springpagination.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable {
  static final long serialVersionUID = 1L;

  String studentName;

  AddressDto addressDto;
  
  String phoneNumber;

}
