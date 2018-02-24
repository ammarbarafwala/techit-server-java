package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.Ticket;
import techit.model.Unit;
import techit.model.User;


@Test(groups = "UnitDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	
	
	@Autowired
    UserDao userDao;
	
	@Autowired
    UnitDao unitDao;
	
	@Autowired
	TicketDao ticketDao;

  @Test
  public void addTechnicianToUnit() {
	  
	  Unit unit = unitDao.getUnit( 1L );
	  User technician = userDao.getUser( 3L );
	  
	  unitDao.addTechnicianToUnit(technician, unit);
	  
	  assert unit.getTechnicians().contains(technician);
  }

  @Test
  public void deleteUnit() {
    Unit unit = unitDao.getUnit( 3L );
    unitDao.deleteUnit(unit);
    assert unitDao.getUnits().contains(unit) == false;
  }

  @Test
  public void getUnit() {
	 assert unitDao.getUnit( 1L ) !=null;
  }

  @Test
  public void getUnitByName() {
    assert unitDao.getUnitByName("Electrical") !=null;
  }

  @Test
  public void getUnits() {
    assert unitDao.getUnits().size() >= 2;
  }

  @Test
  public void removeTechnicianFromUnit() {
	  Unit unit = unitDao.getUnit( 1L );
	  User technician = userDao.getUser( 3L );
	  
	  unitDao.removeTechnicianFromUnit(technician, unit);
	  
	  assert !unit.getTechnicians().contains(technician);
  }

  @Test
  public void saveUnit() {
    Unit unit = new Unit();
    unit.setName("Painting");
    unit.setLocation("FA123");
    unit.setEmail("paint@calstate");
    unit.setDescription("Painters");
    unit.setPhone("234536");
    unit = unitDao.saveUnit(unit);
    assert unit != null;
  }
}
