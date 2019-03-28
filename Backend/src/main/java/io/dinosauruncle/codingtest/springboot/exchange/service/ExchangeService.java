package io.dinosauruncle.codingtest.springboot.exchange.service;

import io.dinosauruncle.codingtest.springboot.exchange.domain.ExchangeVO;

public interface ExchangeService {
	public ExchangeVO getExchangeObj(String transferType, String receiptType);
}
