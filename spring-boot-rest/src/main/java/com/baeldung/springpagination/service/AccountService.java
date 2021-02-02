package com.baeldung.springpagination.service;

import com.baeldung.persistence.dao.Balance;
import com.baeldung.persistence.dao.BalanceDao;
import com.baeldung.persistence.dao.Transaction;
import com.baeldung.persistence.dao.TransactionDao;
import com.baeldung.springpagination.model.BalanceDto;
import com.baeldung.springpagination.model.TransactionDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	private final BalanceDao balanceDao;
	private final TransactionDao trnsactionDao;

	@Autowired
	private ModelMapper modelMapper;

	public AccountService(BalanceDao balanceDao, TransactionDao trnsactionDao) {
		this.balanceDao = balanceDao;
		this.trnsactionDao = trnsactionDao;
	}

	@Transactional
	public void createAccount(BalanceDto balanceDto) {

		Balance balance = this.modelMapper.map(balanceDto, Balance.class);

		balanceDao.save(balance);
	}

	@Transactional
	public void postTransactions(TransactionDto transactionDto) {
		
		if(transactionDto.getAccountNumber()!=null){
			
			Optional<Balance> balance = balanceDao.findById(transactionDto.getAccountNumber());
			BalanceDto balanceDto = BalanceMapper.INSTANCE.toDto(balance.get(), transactionDto);
			balanceDao.save(BalanceMapper.INSTANCE.fromDto(balanceDto));
		}

		Transaction transaction = this.modelMapper.map(transactionDto, Transaction.class);

		trnsactionDao.save(transaction);
	}

	public BalanceDto findBalanceById(String id) {
		Optional<Balance> balance = balanceDao.findById(id);
		return BalanceMapper.INSTANCE.toDto(balance.get());
	}

	public Set<Transaction> findBalances(String id, String fromDate, String toDate, String type) {
		Set<Transaction> balance = trnsactionDao.findTxs(id, toDate(fromDate), toDate(toDate), type);
		return balance;
	}
	
	private Calendar toDate(String date) {

        Calendar cal = null;
        try {
            cal = Calendar.getInstance();
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z").parse(date));
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        } catch (ParseException e) {
        }
        return cal;
    }
	
	/*
	 * public PageDto findAllTxs(String parentId, Integer page,
	 * Integer size) { Pageable pageable = PageRequest.of(page, size);
	 * Page<AccountQueryResult> businessAccountsPage =
	 * repository.findAllChildrenWithDetails(id, pageable); return
	 * PageMapper.INSTANCE.toPageDto(businessAccountsPage,
	 * objectMapper); }
	 */
}