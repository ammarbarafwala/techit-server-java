package techit.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import techit.model.Ticket;
import techit.model.User;
import techit.model.dao.TicketDao;
import techit.model.dao.UserDao;
import techit.rest.error.RestException;
import techit.security.SecurityUtils;

@RestController
public class TicketController {

	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public List<Ticket> getTickets(@ModelAttribute("currentUser") User currentUser) {
		if (currentUser.getPost() == User.Position.SYS_ADMIN || currentUser.getPost() == User.Position.SUPERVISING_TECHNICIAN)
			return ticketDao.getTickets();

		throw new RestException(403, "Unauthorized Access");
	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.POST)
	public Ticket addTicket(@RequestBody Ticket ticket,@ModelAttribute("currentUser") User currentUser) {
		
		ticket.setRequester(currentUser);
		
		return ticketDao.saveTicket(ticket);
	}
	
	@RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET)
	public Ticket getTicket(@ModelAttribute("currentUser") User currentUser,@PathVariable Long ticketId) {
		Ticket ticket =ticketDao.getTicket(ticketId);
		if(ticket.getRequester().getId()==currentUser.getId() ||
		 currentUser.getPost() == User.Position.SYS_ADMIN || currentUser.getPost() == User.Position.SUPERVISING_TECHNICIAN)
			return ticket;

		throw new RestException(403, "Unauthorized Access");
	}
	
	@RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.PUT)
	public Ticket editTicket(@PathVariable Long ticketId, @RequestBody Ticket ticket,
			@ModelAttribute("currentUser") User currentUser) {
		
		Long userId =ticketDao.getTicket(ticketId).getRequester().getId();
		
		if(userId==currentUser.getId() ||
		   currentUser.getPost() == User.Position.SYS_ADMIN || currentUser.getPost() == User.Position.SUPERVISING_TECHNICIAN) {
			
			ticket.setId(ticketId);
			ticket.setRequester(currentUser);
			return ticketDao.saveTicket(ticket);
		}
			
		throw new RestException(403, "Unauthorized Access");
	}
	
	@RequestMapping(value = "/tickets/{ticketId}/technicians", method = RequestMethod.GET)
	public List<User> getTicketTechnicians(@PathVariable Long ticketId,@ModelAttribute("currentUser") User currentUser) {
		
		if(currentUser.getPost() == User.Position.SYS_ADMIN || currentUser.getPost() == User.Position.SUPERVISING_TECHNICIAN) {
			
			Ticket ticket=ticketDao.getTicket(ticketId);
			return ticket.getTechnicians();
		}
			
		throw new RestException(403, "Unauthorized Access");
	}
	
	@RequestMapping(value = "/tickets/{ticketId}/technicians/{userId}", method = RequestMethod.PUT)
	public Ticket assignTechnician(@PathVariable Long ticketId,@PathVariable Long userId,@ModelAttribute("currentUser") User currentUser) {
		User user=userDao.getUser(userId);
		System.out.println("hello");
		if((currentUser.getPost() != User.Position.SYS_ADMIN 
				&& (currentUser.getPost() != User.Position.SUPERVISING_TECHNICIAN || currentUser.getUnit().getId() != user.getUnit().getId())
				&& (currentUser.getPost() != User.Position.TECHNICIAN || currentUser.getId()!=userId))
				|| (user.getPost()!=User.Position.TECHNICIAN || user.getPost()!=User.Position.SUPERVISING_TECHNICIAN)) {
			
			throw new RestException(403, "Unauthorized Access");
		}
		System.out.println("hi");
		Ticket ticket=ticketDao.getTicket(ticketId);
		
		for(User u:ticket.getTechnicians())
		{
			if(u.getId()==userId)
			{
				throw new RestException(400, "Bad Request");
			}
		}
		
		ticket.getTechnicians().add(user);
		ticket.setId(ticketId);
		return ticketDao.saveTicket(ticket);
		
	}
}
