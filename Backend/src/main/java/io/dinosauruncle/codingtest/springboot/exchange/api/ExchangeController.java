package io.dinosauruncle.codingtest.springboot.exchange.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.dinosauruncle.codingtest.springboot.exchange.domain.ExchangeVO;
import io.dinosauruncle.codingtest.springboot.exchange.service.ExchangeService;

@Controller
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@RequestMapping(value="/exchange/{transferType}/{receiptType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getExchangeList(@PathVariable String transferType, @PathVariable String receiptType){
		ExchangeVO exchangeObj = exchangeService.getExchangeObj(transferType, receiptType);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("exchangeObj", exchangeObj);
		return result;
	}
}
