package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

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
    public void saveUser()
    {
        User user = new User();
        user.setUsername( "Tom" );
        user.setPassword( "abcd" );
        user.setFirstName("Thomas");
        user.setLastName("Edison");
        user = userDao.saveUser( user );

        assert user.getId() != null;
    }

    @Test
    public void getUser()
    {
        assert userDao.getUser( 1L ).getUsername().equalsIgnoreCase( "admin" );
    }
    
    @Test
    public void getUserByUsername()
    {
    	assert userDao.getUserByUsername("ammar").getUsername().equals("ammar");
    }
    
    @Test
    public void getSupervisors() {
    	
    	Unit unit = unitDao.getUnit(7L);
    	assert userDao.getSupervisors(unit).size()>=2;
    }

    @Test
    public void getTechnicians() {
    	
    	Unit unit = unitDao.getUnit(7L);
    	assert userDao.getTechnicians(unit).size()>=2;
    }

    @Test
    public void getUsers()
    {
        assert userDao.getUsers().size() >= 2;
    }
    
    @Test
    public void authenticateUser()
    {
    	assert userDao.authenticateUser("ammar", "1234").getUsername().equals("ammar");
    }
    
    @Test
    public void getTechniciansAndSupervisors() {
    	Unit unit = unitDao.getUnit(7L);
    	assert userDao.getTechniciansAndSupervisors(unit).size() >= 4;
    }
    
    @Test
    public void getTechniciansAssigned() {
    	assert userDao.getTechniciansAssigned(ticketDao.getTicket(1L)).size()>=2;
    }

}
