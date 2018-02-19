package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.User;

public interface TicketDao {

	Ticket getTicket(Long id);
	
	List<Ticket> getTickets();
	
	boolean checkAssignment(User technician, Ticket ticket);
	
	Ticket saveTicket(Ticket ticket);
	
	String getRequestorEmail(Ticket ticket);
	
	List<User> getTechniciansAssigned(Ticket ticket);
	
	List<UpdateDetails> getTicketUpdates(Ticket ticket);
	}
