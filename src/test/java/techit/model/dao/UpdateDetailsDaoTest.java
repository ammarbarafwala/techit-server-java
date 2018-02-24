package techit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import techit.model.UpdateDetails;


@Test(groups = "UpdateDetailsDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
class UpdateDetailsDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	UpdateDetailsDao updateDetailsDao;
	@Autowired
	TicketDao ticketDao;
	@Autowired
	UserDao userDao;
	
	@Test
	void saveUpdate() {
		UpdateDetails upd=new UpdateDetails();
		upd.setUpdateDetails("Test");
		upd = updateDetailsDao.saveUpdate(upd);
		assert upd.getId() != null;
	}
	
	@Test
	void getTicketUpdates() {
		assert updateDetailsDao.getTicketUpdates(ticketDao.getTicket(1L)).size()>=2;
	}
	
	@Test
	void getUpdate() {
		assert updateDetailsDao.getUpdate(2L).getId()!=null;
	}
	
	@Test
	void getUpdatesByModifier() {
		assert updateDetailsDao.getUpdatesByModifier(ticketDao.getTicket(1L), userDao.getUser(2L)).size()>=2;
	}

}
