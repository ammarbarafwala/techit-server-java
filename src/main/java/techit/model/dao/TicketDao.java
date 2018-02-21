package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.User;

public interface TicketDao {

	Ticket getTicket(Long id);
	
	Ticket GetUserTicket(User user);
	
	Ticket searchTicket(Long id);
	
	List<User> getTechniciansAssigned(Ticket ticket);
	
	String getRequestorEmail(Ticket ticket);
	
	Ticket saveTicket(Ticket ticket);
	
	List<Ticket> getTickets();
	
	boolean checkAssignment(User technician, Ticket ticket);
	
	void assignTechnician(User technician);
	
}
