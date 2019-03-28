package io.dinosauruncle.codingtest.springboot.exchange.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString

public class ExchangeVO {
	
	private String exchangeName;
	private double exchangeValue;
	
	public ExchangeVO(String exchangeName, double exchangeValue) {
		this.exchangeName = exchangeName;
		this.exchangeValue = exchangeValue;
	}
	
	

}
