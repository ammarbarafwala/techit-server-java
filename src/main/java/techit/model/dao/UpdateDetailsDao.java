package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.User;

public interface UpdateDetailsDao {
	
	UpdateDetails saveUpdate(UpdateDetails update);
	
	List<UpdateDetails> getTicketUpdates(Ticket ticket); 
	
	UpdateDetails getUpdate(Long id);
	
	List<UpdateDetails> getUpdatesByModifier(Ticket ticket, User user); 

}
