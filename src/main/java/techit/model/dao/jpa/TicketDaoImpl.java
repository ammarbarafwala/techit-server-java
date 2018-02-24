package techit.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import techit.model.Ticket;
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
		// not sure we need to do this. we can get technicians assigned
		return ticket.getTechnicians().contains(technician);
	}

	@Override
	@Transactional
	public Ticket saveTicket(
			Ticket ticket) {
		
		return entityManager.merge( ticket );
		
	}


	@Override
	public void assignTechnician(User technician, Ticket ticket) {
		ticket.getTechnicians().add(technician);
		technician.getTicketsAssigned().add(ticket);
		entityManager.merge(ticket);
		
	}

	@Override
	public List<Ticket> getUserTickets(User user) {
		return entityManager.createQuery("from Ticket where requesterDetails = :user ", Ticket.class)
				.setParameter("user", user).getResultList();
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
		entityManager.remove(ticket);
		
	}
	
}
