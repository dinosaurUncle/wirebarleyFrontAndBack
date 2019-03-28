package io.dinosauruncle.codingtest.springboot.nation.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.dinosauruncle.codingtest.springboot.nation.domain.Nation;
import io.dinosauruncle.codingtest.springboot.nation.domain.NationRepository;

@Service
@Transactional
public class NationServiceImpl implements NationService {
	private final NationRepository nationRepository;
	
	public NationServiceImpl(NationRepository nationRepository) {
		this.nationRepository = nationRepository;
	}
	
	public void save(Nation nation) {
		nationRepository.save(nation);
	}

	@Override
	public List<Nation> findAll() {
		return nationRepository.findAll();
	}
	//EXCHANGE_TYPE
	public List<Nation> findByExchangeType(String exchangeType){
		return nationRepository.findByExchangeType(exchangeType);
	}
	

}
