package com.baeldung.springpagination.model;

import java.io.Serializable;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private EAddressType type;

  @NotNull
  private String streetLine1;

  private String streetLine2;

  private String streetLine3;

  @NotNull
  private String zipCode;

  @NotNull
  private String countryCode;

  @NotNull
  private String stateCode;

  @NotNull
 // @Size(max = 255)
  private String cityName;

  private String apartmentNumber;

  private boolean primary;
}
