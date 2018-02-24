package techit.model.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.Unit;


@Test(groups = "UnitDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests{
	
    @Autowired
    UnitDao unitDao;
    @Autowired
    UserDao userDao;

	@Test
	void getUnit() {
		assert unitDao.getUnit(1L)!=null;
	}
	@Test
	void getUnits() {
		assert unitDao.getUnits().size()>=2;
	}
	
	@Test
	void saveUnit() {
		Unit unit = new Unit();
		unit.setName("Developing");
		unit.setDescription("Developing");
		unit.setEmail("d@g.com");
		unit.setLocation("AB");
		unit.setPhone("789456");
		unit = unitDao.saveUnit(unit);
		assert unit.getId() != null;
	}
	
	@Test
	void getUnitByName() {
		assert unitDao.getUnitByName("Testing").getId() !=null;
	}
	
	@Test
	void deleteUnit() {
		unitDao.deleteUnit(unitDao.getUnit(1L));
		assert unitDao.getUnit(1L) == null;
	}
	
	@Test
	void addTechnicianToUnit() {
		unitDao.addTechnicianToUnit(userDao.getUser(6L),unitDao.getUnit(1L));
		assert userDao.getUser(6L).getUnit().getId() == 1L;
	}
	@Test
	void removeTechnicianFromUnit() {
		unitDao.removeTechnicianFromUnit(userDao.getUser(2L), unitDao.getUnit(7L));
		assert userDao.getUser(2L).getUnit()==null;
	}
}
