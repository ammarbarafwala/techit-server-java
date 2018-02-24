package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.Ticket;
import techit.model.Unit;
import techit.model.UpdateDetails;
import techit.model.User;


@Test(groups = "UpdateDetailsDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UpdateDetailsDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
    UserDao userDao;
	
	@Autowired
    UnitDao unitDao;
	
	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	UpdateDetailsDao updateDao;
	
  @Test
  public void getTicketUpdates() {
	  Ticket ticket = ticketDao.getTicket( 1L );
	  assert updateDao.getTicketUpdates(ticket).size() >=1;
  }

  @Test
  public void getUpdate() {
   assert updateDao.getUpdate( 1L ) !=null;
  }

  @Test
  public void getUpdatesByModifier() {
	  Ticket ticket = ticketDao.getTicket( 1L );
	  User user = userDao.getUser( 1L );
    assert updateDao.getUpdatesByModifier(ticket, user).size() >= 1;
  }

  @Test
  public void saveUpdate() {
    UpdateDetails update = new UpdateDetails();
    Ticket ticket = ticketDao.getTicket( 1L );
	 User user = userDao.getUser( 1L );
    update.setModifier(user);
    update.setTicket(ticket);
    update = updateDao.saveUpdate(update);
    
    assert update !=null;
    
  }
}
