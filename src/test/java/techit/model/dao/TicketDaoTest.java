package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.Ticket;
import techit.model.User;

@Test(groups = "TicketDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TicketDaoTest extends AbstractTransactionalTestNGSpringContextTests{
	@Autowired
	TicketDao ticketDao;
	@Autowired
	UserDao userDao;
	
	@Test
	void getTicket() {
		assert ticketDao.getTicket(1L).getId()!=null;
	}
	
	@Test
	void getTickets() {
		assert ticketDao.getTickets().size()>=2;
	}
	
	@Test
	void checkAssignment() {
		assert ticketDao.checkAssignment(userDao.getUser(1L), ticketDao.getTicket(1L));
	}
	@Test
	void saveTicket() {
		Ticket t=new Ticket();
		t.setSubject("PC error");
		t=ticketDao.saveTicket(t);
		assert t.getId()!=null;
	}
	@Test
	void assignTechnician() {
		ticketDao.assignTechnician(userDao.getUser(4L), ticketDao.getTicket(2L));
		for(Ticket t: userDao.getUser(4L).getTicketsAssigned()) {
			if(t.getId()==2L) {
				assert true;
				return;
			}
		}
		assert false;
	}
	
	@Test
	void getUserTickets() {
		assert ticketDao.getUserTickets(userDao.getUser(3L)).size()>=2;
	}
	
	@Test
	void deleteTicket() {
		ticketDao.deleteTicket(ticketDao.getTicket(4L));
		assert ticketDao.getTicket(4L)==null;
	}
}
