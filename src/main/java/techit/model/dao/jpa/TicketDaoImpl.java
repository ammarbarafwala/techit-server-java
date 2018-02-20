package techit.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.User;
import techit.model.dao.TicketDao;



@Repository
public class TicketDaoImpl implements TicketDao {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Ticket getTicket(Long id) {

		return entityManager.find( Ticket.class, id );
		
	}

	@Override
	public List<Ticket> getTickets() {
		return entityManager.createQuery( "from Ticket order by id", Ticket.class )
	            .getResultList();
	}

	@Override
	public boolean checkAssignment(User technician, Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public Ticket saveTicket(Ticket ticket) {
		
		return entityManager.merge( ticket );
		
	}

	@Override
	public String getRequestorEmail(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getTechniciansAssigned(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpdateDetails> getTicketUpdates(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateDetails saveUpdate(UpdateDetails update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignTechnician(User technician) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ticket GetUserTicket(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket searchTicket(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket saveUpdate(Ticket update) {
		// TODO Auto-generated method stub
		return null;
	}

}
