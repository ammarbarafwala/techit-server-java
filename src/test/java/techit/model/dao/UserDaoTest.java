package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.Ticket;
import techit.model.Unit;
import techit.model.User;


@Test(groups = "UserDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests { 
	
	@Autowired
    UserDao userDao;
	
	@Autowired
    UnitDao unitDao;
	
	@Autowired
	TicketDao ticketDao;

    @Test
    public void getUser()
    {
        assert userDao.getUser( 1L ).getUsername().equalsIgnoreCase( "admin" );
    }

    @Test
    public void getUsers()
    {
        assert userDao.getUsers().size() >= 2;
    }
    
    

    @Test
    public void saveUser()
    {
        User user = new User();
        user.setUsername( "Tom" );
        user.setPassword( "abcd" );
        user.setFirstName("Tom");
        user.setLastName("Foss");
        user = userDao.saveUser( user );

        assert user.getId() != null;
    }

  @Test
  public void authenticateUser() {
        assert userDao.authenticateUser("admin", "abcd") != null;
        assert userDao.authenticateUser("Bob", "abcd") == null;
  }

  @Test
  public void getSupervisors() {
	  Unit unit = unitDao.getUnit( 1L );
	  assert userDao.getSupervisors(unit).size() >= 1;
  }

  @Test
  public void getTechnicians() {
	  Unit unit = unitDao.getUnit( 1L );
	  assert userDao.getTechnicians(unit).size() >= 1;
  }

  @Test
  public void getTechniciansAndSupervisors() {
	  Unit unit = unitDao.getUnit( 1L );
	  assert userDao.getTechniciansAndSupervisors(unit).size() >= 2;
  }

  @Test
  public void getTechniciansAssigned() {
    Ticket ticket = ticketDao.getTicket( 1L );
    assert userDao.getTechniciansAssigned(ticket).size() >= 1;
  }

  @Test
  public void getUserByUsername() {
    assert userDao.getUserByUsername("admin") != null;
  }

 
}



