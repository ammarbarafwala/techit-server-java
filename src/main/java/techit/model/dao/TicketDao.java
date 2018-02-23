package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.User;

public interface TicketDao {

	Ticket getTicket(Long id);
	
	List<Ticket> getUserTickets(User user);
	
	Ticket saveTicket(Ticket ticket);
	
	List<Ticket> getTickets();
	
	boolean checkAssignment(User technician, Ticket ticket);

	void assignTechnician(User technician, Ticket ticket);
	
	void deleteTicket(Ticket ticket);
	
	Ticket getLastTicketCreated(User user);
	
	
}
