package io.dinosauruncle.codingtest.springboot.nation.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.dinosauruncle.codingtest.springboot.nation.domain.Nation;
import io.dinosauruncle.codingtest.springboot.nation.service.NationService;

@Controller
public class NationController {
	@Autowired
	private NationService nationService;
	
	@RequestMapping(value="/nation", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getNationList(){
		
		List<Nation> nationList = nationService.findAll();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("nationList", nationList);
		return result;
	}
	
	@RequestMapping(value="/nation/{exchangeType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getNationListFindByExchangeType(@PathVariable String exchangeType){		
		List<Nation> nationList = nationService.findByExchangeType(exchangeType);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("nationList", nationList);
		return result;
	}
	
	@RequestMapping(value="/nation", method=RequestMethod.POST,
			headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> saveNation(@RequestBody Nation nation){
		boolean saveResult = false;
		if(nation != null) {
			nationService.save(nation);
			saveResult =  true;
		} 			
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", saveResult);
		return result;
	}


}
