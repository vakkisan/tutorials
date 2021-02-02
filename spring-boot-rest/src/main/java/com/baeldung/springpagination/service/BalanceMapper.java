package com.baeldung.springpagination.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.baeldung.persistence.dao.Balance;
import com.baeldung.springpagination.model.BalanceDto;
import com.baeldung.springpagination.model.TransactionDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class BalanceMapper {
	public static final BalanceMapper INSTANCE = Mappers.getMapper(BalanceMapper.class);

	@Mapping(target = "accountNumber", source = "accountNumber")
	@Mapping(target = "lastUpdateTimestamp", source = "lastUpdateTimestamp")
	@Mapping(target = "balance", source = "balance")
	public abstract Balance fromDto(BalanceDto dto);

	public BalanceDto toDto(Balance balance, TransactionDto transactionDto) {
		double updated = 0;
		if ("DEPOSIT".equals(transactionDto.getType())) {
			updated = balance.getBalance() + transactionDto.getAmount();
		} else {
			updated = balance.getBalance() - transactionDto.getAmount();
		}
		return BalanceDto.builder().accountNumber(balance.getAccountNumber()).balance(updated)
				.lastUpdateTimestamp(balance.getLastUpdateTimestamp()).build();
	}
	
	@Mapping(target = "accountNumber", source = "accountNumber")
	@Mapping(target = "lastUpdateTimestamp", source = "lastUpdateTimestamp")
	@Mapping(target = "balance", source = "balance")
	public abstract BalanceDto toDto(Balance dto);

}
