package techit.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import techit.model.Ticket;
import techit.model.UpdateDetails;
import techit.model.User;
import techit.model.dao.TicketDao;
import techit.model.dao.UpdateDetailsDao;
import techit.rest.error.RestException;

@RestController
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UpdateDetailsDao updateDao;


    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public List<Ticket> getTickets()
    {
        return ticketDao.getTickets();
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
    public Ticket getTicket( @PathVariable Long id )
    {
        return ticketDao.getTicket( id );
    }

    @RequestMapping(value= "/tickets/{ticketId}/status/{status}", method = RequestMethod.PUT)
    public Ticket setStatus(@ModelAttribute("currentUser") User user, @PathVariable Long ticketId, @RequestBody String description, @PathVariable String status) {
        if (user.getPost()!= User.Position.SYS_ADMIN && user.getPost()!= User.Position.SUPERVISING_TECHNICIAN
                && user.getPost()!= User.Position.TECHNICIAN){
            throw new RestException(403, "Unauthorized Access");
        }
        Ticket ticket = ticketDao.getTicket(ticketId);
        if (user.getPost() == User.Position.TECHNICIAN && !user.getTicketsAssigned().contains(ticket)) {
            throw new RestException(403, "Unauthorized Access, You have no permissions for this action");
        }
        ticket.setProgress(Ticket.Progress.valueOf(status));
        UpdateDetails update = new UpdateDetails();
        update.setModifier(user);
        update.setModifiedDate(new Date());
        update.setTicket(ticket);
        update.setUpdateDetails(description);
        update = updateDao.saveUpdate(update);

        if (update != null)
        return ticketDao.saveTicket(ticket);
        else
            throw new RestException(400, "An error occured");
    }

    @RequestMapping(value= "/tickets/{ticketId}/priority/{priority}", method = RequestMethod.PUT)
    public Ticket setPriority(@ModelAttribute("currentUser") User user, @PathVariable Long ticketId, @PathVariable String priority) {
        if (user.getPost()!= User.Position.SYS_ADMIN && user.getPost()!= User.Position.SUPERVISING_TECHNICIAN
                && user.getPost()!= User.Position.TECHNICIAN){
            throw new RestException(403, "Unauthorized Access");
        }
        Ticket ticket = ticketDao.getTicket(ticketId);
        if (user.getPost() == User.Position.TECHNICIAN && !user.getTicketsAssigned().contains(ticket)) {
            throw new RestException(403, "Unauthorized Access, You have no permissions for this action");
        }
        ticket.setPriority(Ticket.Priority.valueOf(priority));
        return ticketDao.saveTicket(ticket);

    }

    @RequestMapping(value = "/tickets/{ticketid}/updates", method = RequestMethod.POST)
    public UpdateDetails createTicketUpdate(@ModelAttribute("currentUser") User user,
                                            @PathVariable Long ticketid, @RequestBody UpdateDetails update){
        if (user.getPost()!= User.Position.SYS_ADMIN && user.getPost()!= User.Position.SUPERVISING_TECHNICIAN
                && user.getPost()!= User.Position.TECHNICIAN){
            throw new RestException(403, "Unauthorized Access");
        }
        Ticket ticket = ticketDao.getTicket(ticketid);
        if (user.getPost() == User.Position.TECHNICIAN && !user.getTicketsAssigned().contains(ticket)) {
            throw new RestException(403, "Unauthorized Access, You Have no permissions for this action");
        }
        update.setModifier(user);
        update.setModifiedDate(new Date());
        update.setTicket(ticket);

        return updateDao.saveUpdate(update);

    }


}
