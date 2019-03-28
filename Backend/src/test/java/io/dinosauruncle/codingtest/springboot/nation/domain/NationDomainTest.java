package io.dinosauruncle.codingtest.springboot.nation.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NationDomainTest {
	protected static Logger logger = Logger.getLogger(NationDomainTest.class.getName());
	
	@Autowired
	NationRepository repository;
	
	@Test @Ignore
	public void testSave() {
		Nation nation = new Nation();
		nation.setName("미국");
		nation.setExchangeValue("USD");
		nation.setExchangeType("trans");
		assertThat(nation.isNew()).isTrue();
		repository.save(nation);
		assertThat(nation.isNew()).isFalse();
	}
	
	@Test @Ignore
	public void testFindAll() {
		Nation nation = new Nation();
		nation.setName("미국");
		nation.setExchangeValue("USD");
		nation.setExchangeType("trans");		
		repository.save(nation);
		List<Nation> nationList  = repository.findAll();
		Iterator<Nation> iterator = nationList.iterator();
		while (iterator.hasNext()) {
			Nation nationObj = iterator.next();
			logger.info(nationObj.toString());
		}
	}
	
	@Test @Ignore
	public void testFindExchangeTypeNationList() {
		List<Nation> nationList  = repository.findByExchangeType("trans");
		Iterator<Nation> iterator = nationList.iterator();
		while (iterator.hasNext()) {
			Nation nationObj = iterator.next();
			logger.info(nationObj.toString());
		}
	}

}
