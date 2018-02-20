package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.User;

public interface TicketDao {

	Ticket getTicket(Long id);
	
	Ticket GetUserTicket(User user);
	
	Ticket searchTicket(Long id);
	
	List<UpdateDetails> getTicketUpdates(Ticket ticket);
	
	List<User> getTechniciansAssigned(Ticket ticket);
	
	String getRequestorEmail(Ticket ticket);
	
	UpdateDetails saveUpdate(UpdateDetails update);
	
	Ticket saveTicket(Ticket ticket);
	
	List<Ticket> getTickets();
	
	Ticket saveUpdate(Ticket update);
	
	boolean checkAssignment(User technician, Ticket ticket);
	
	void assignTechnician(User technician);
	
}
