package com.baeldung.persistence.dao;

import java.util.Calendar;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionDao extends CrudRepository<Transaction, Long> {
    
	@Query(value = "select * from transaction  Where (account_number)=(:id)  "
			+ " AND (transaction_ts) > (:fromDate)   AND (transaction_ts) <(:toDate)"
			+ " AND (type)  = (:type) order by transaction_ts desc", nativeQuery = true)
	public Set<Transaction> findTxs(@Param("id") String id, @Param("fromDate") Calendar fromDate,
			@Param("toDate") Calendar toDate, @Param("type") String type);
}
