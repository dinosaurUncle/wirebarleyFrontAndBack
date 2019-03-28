package io.dinosauruncle.codingtest.springboot.nation.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, Long> {
	public List<Nation> findByExchangeType(String exchangeType);
}
