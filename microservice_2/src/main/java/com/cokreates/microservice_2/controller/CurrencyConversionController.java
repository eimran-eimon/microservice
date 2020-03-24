package com.cokreates.microservice_2.controller;


import com.cokreates.microservice_2.CurrencyConversionBean;
import com.cokreates.microservice_2.proxy.ExchangeConversionMultipleProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	ExchangeConversionMultipleProxy exchangeConversionMultipleProxy;

	public CurrencyConversionController(ExchangeConversionMultipleProxy exchangeConversionMultipleProxy) {
		this.exchangeConversionMultipleProxy = exchangeConversionMultipleProxy;
	}

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,
	                                              @PathVariable String to,
	                                              @PathVariable BigDecimal quantity){

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity =
				new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
						CurrencyConversionBean.class,
						uriVariables
				);

		CurrencyConversionBean response = responseEntity.getBody();
		return new CurrencyConversionBean(response.getId(),
				from,
				to ,
				response.getConversionMultiple(),
				quantity,
				quantity.multiply(response.getConversionMultiple()),
				response.getPort());

	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
	                                              @PathVariable String to,
	                                              @PathVariable BigDecimal quantity){

		// using feign client
		CurrencyConversionBean response = exchangeConversionMultipleProxy.getExchangeValue(from, to);

		logger.info("{}", response);

		return new CurrencyConversionBean(response.getId(),
				from,
				to,
				response.getConversionMultiple(),
				quantity,
				quantity.multiply(response.getConversionMultiple()),
				response.getPort());

	}


}
