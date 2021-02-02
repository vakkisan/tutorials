package com.baeldung.springpagination.model;

import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class PageDto<T> implements Serializable {

  public static final long serialVersionUID = 1L;

  Long totalElements;
  Integer totalPages;
  Integer number;
  Integer numberOfElements;
  Boolean hasNext;
  List<T> content;

}
