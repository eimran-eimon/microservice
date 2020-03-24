package com.cokreates.microservice_2.proxy;


import com.cokreates.microservice_2.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-service",url = "localhost:8000")
public interface ExchangeConversionMultipleProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getExchangeValue(@PathVariable String from,
	                                               @PathVariable String to);

}
