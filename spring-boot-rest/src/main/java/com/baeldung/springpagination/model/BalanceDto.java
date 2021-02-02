package com.baeldung.springpagination.model;

import java.io.Serializable;
import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDto implements Serializable {
  static final long serialVersionUID = 1L;

  String accountNumber;

  Calendar lastUpdateTimestamp;
  
  Double balance;

}
