package techit.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.User;
import techit.model.dao.UpdateDetailsDao;

public class UpdateDetailsDaoImpl implements UpdateDetailsDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public UpdateDetails saveUpdate(UpdateDetails update) {
		return entityManager.merge(update);
	}

	@Override
	public List<UpdateDetails> getTicketUpdates(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticket.getUpdates();
	}

	@Override
	public UpdateDetails getUpdate(Long id) {
		return entityManager.find(UpdateDetails.class, id);
	}

	@Override
	public List<UpdateDetails> getUpdatesByModifier(Ticket ticket, User user) {
		return entityManager.createQuery("from UpdateDetails where modifier = :user", UpdateDetails.class)
				.setParameter("user", user.getUsername()).getResultList();
	}


}
