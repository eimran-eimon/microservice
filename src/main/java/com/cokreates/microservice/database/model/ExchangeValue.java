package com.cokreates.microservice.database.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ExchangeValue {
	@Id
	private Long id;

	private String currencyFrom;

	private String currencyTo;

	private BigDecimal conversionMultiple;

	private Integer port;

	public ExchangeValue() {
	}

	public ExchangeValue(String currencyFrom, String currencyTo, BigDecimal conversionMultiple, Integer port) {
		this.currencyFrom  = currencyFrom;
		this.currencyTo = currencyTo;
		this.conversionMultiple = conversionMultiple;
		this.port = port;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return currencyFrom;
	}

	public void setFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getTo() {
		return currencyTo;
	}

	public void setTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public int getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
