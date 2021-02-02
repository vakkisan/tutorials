package com.baeldung.persistence.dao;

import org.springframework.data.repository.CrudRepository;

public interface BalanceDao extends CrudRepository<Balance, String> {

}
