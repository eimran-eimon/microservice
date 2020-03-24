package com.cokreates.microservice.controller;


import com.cokreates.microservice.database.model.ExchangeValue;
import com.cokreates.microservice.database.repository.ExchangeValueRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeValueController {

	ExchangeValueRepository exchangeValueRepository;

	private final Environment environment;

	public ExchangeValueController(ExchangeValueRepository exchangeValueRepository, Environment environment) {
		this.exchangeValueRepository = exchangeValueRepository;
		this.environment = environment;
	}


	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
		ExchangeValue exchangeValue = exchangeValueRepository.findByCurrencyFromAndCurrencyTo(from, to);
		exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
		return  exchangeValue;
	}

}
