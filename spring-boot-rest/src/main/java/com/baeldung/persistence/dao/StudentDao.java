package com.baeldung.persistence.dao;

import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, String> {

}
