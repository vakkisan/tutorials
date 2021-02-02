package com.baeldung.springpagination.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
public class AccountPageDto extends PageDto<BalanceDto> {

  public static final long serialVersionUID = 1L;

}
