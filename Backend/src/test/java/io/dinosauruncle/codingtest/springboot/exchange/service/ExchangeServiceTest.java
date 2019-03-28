package io.dinosauruncle.codingtest.springboot.exchange.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.logging.Logger;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.dinosauruncle.codingtest.springboot.exchange.domain.ExchangeVO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class ExchangeServiceTest {
	protected static Logger logger = Logger.getLogger(ExchangeServiceTest.class.getName());

	@Autowired
	ExchangeService exchangeService;
	
	private String transferType = null;
	private String receiptType = null;
	private ExchangeVO exchangeObj = null;
	
	@Before
	public void setUp() {
		transferType = "USD";
		receiptType = "KRW";
		exchangeObj = exchangeService.getExchangeObj(transferType, receiptType);
	}
	
	@Test @Ignore
	public void getExchangeCombinenameCheck() {
		assertThat(exchangeObj.getExchangeName()).isNotNull();
		assertThat(exchangeObj.getExchangeName(), CoreMatchers.is("USDKRW"));
		logger.info("exchangeCombineName: " +exchangeObj.getExchangeName());
	}
	
	@Test @Ignore
	public void getExchangeInfoNotNullCheck() {		
		assertThat(exchangeObj.getExchangeValue()).isNotNull();		
		logger.info("exchangeValue: " + Double.toString(exchangeObj.getExchangeValue()));
	}
	
	
}
