package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.UpdateDetails;

public interface UpdateDetailsDao {
	
	UpdateDetails saveUpdate(UpdateDetails update);
	
	List<UpdateDetails> getTicketUpdates(Ticket ticket);
	
	Ticket saveUpdate(Ticket update);

}
