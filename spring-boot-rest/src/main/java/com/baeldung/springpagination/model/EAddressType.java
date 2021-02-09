package com.baeldung.springpagination.model;

public enum EAddressType {

  WORKPLACE("WORKPLACE"),
  RESIDENTIAL("RESIDENTIAL"),
  MAILING("MAILING"),
  BILLING("BILLING"),
  OTHER("OTHER");

  private final String value;

  EAddressType(final String value) {
    this.value = value;
  }

  public boolean equals(String otherValue) {
    return value.equals(otherValue);
  }

  public String toString() {
    return this.value;
  }
}
