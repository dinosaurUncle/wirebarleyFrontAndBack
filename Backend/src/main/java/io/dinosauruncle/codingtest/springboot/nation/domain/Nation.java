package io.dinosauruncle.codingtest.springboot.nation.domain;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Nation extends AbstractPersistable<Long> {
	private String name;
	private String exchangeValue;
	private String exchangeType;
	
	public Nation(String name, String exchangeValue, String exchangeType) {
		this.name = name;
		this.exchangeValue = exchangeValue;
		this.exchangeType = exchangeType;
	}
	
	
}
