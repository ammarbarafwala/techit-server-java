package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.Ticket;
import techit.model.User;


@Test(groups = "TicketDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TicketDaoTest extends AbstractTransactionalTestNGSpringContextTests  {
	
	@Autowired
    UserDao userDao;
	
	@Autowired
    UnitDao unitDao;
	
	@Autowired
	TicketDao ticketDao;
	
	@Test
	  public void assignTechnician() {
		Ticket ticket = ticketDao.getTicket( 1L );
		User technician = userDao.getUser( 1L );
	    ticketDao.assignTechnician(technician, ticket);
	    
	    assert ticket.getTechnicians().contains(technician);
	  }

	  @Test
	  public void checkAssignment() {
		  Ticket ticket = ticketDao.getTicket( 1L );
			User technician = userDao.getUser( 1L );
			assert ticketDao.checkAssignment(technician, ticket) == true;
	  }

	  @Test
	  public void deleteTicket() {
		  Ticket ticket = ticketDao.getTicket( 2L );
		  ticketDao.deleteTicket(ticket);
		  assert ticketDao.getTickets().contains(ticket) == false;
	  }

	  @Test
	  public void getLastTicketCreated() {
		  User user = userDao.getUser( 1L );
		  assert ticketDao.getLastTicketCreated(user) != null;
	  }

	  @Test
	  public void getTicket() {
	    assert ticketDao.getTicket( 1L ) != null;
	  }

	  @Test
	  public void getTickets() {
		  assert ticketDao.getTickets().size() >=2;
	  }

	  @Test
	  public void getUserTickets() {
		  User user = userDao.getUser( 1L );
	    assert ticketDao.getUserTickets(user).size() >=1; 
	  }

	  @Test
	  public void saveTicket() {
		  Ticket ticket = new Ticket();
		  ticket.setLocation("ESCT");
		  ticket = ticketDao.saveTicket(ticket);
		  assert ticket !=null;
	  }
}
