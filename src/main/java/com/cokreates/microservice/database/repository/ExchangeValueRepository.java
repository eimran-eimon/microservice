package com.cokreates.microservice.database.repository;


import com.cokreates.microservice.database.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	public ExchangeValue findByCurrencyFromAndCurrencyTo(String from, String to);
}
