package io.dinosauruncle.codingtest.springboot.nation.service;

import java.util.List;

import io.dinosauruncle.codingtest.springboot.nation.domain.Nation;

public interface NationService {
	public void save(Nation nation);
	public List<Nation> findAll();
	public List<Nation> findByExchangeType(String exchangeType);
}
