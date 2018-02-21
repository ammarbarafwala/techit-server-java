package techit.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.dao.UpdateDetailsDao;

public class UpdateDetailsDaoImpl implements UpdateDetailsDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public UpdateDetails saveUpdate(UpdateDetails update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpdateDetails> getTicketUpdates(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket saveUpdate(Ticket update) {
		// TODO Auto-generated method stub
		return null;
	}

}
