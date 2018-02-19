package techit.model.dao;

import java.util.List;

import techit.model.Ticket;
import techit.model.User;

public interface UserDao {

    User getUser( Long id );

    List<User> getUsers();
    
    User getUserByUsername(String username);
    
    String getEmail(User user);

    User saveUser( User user );
    
    Ticket getLastTicketCreated(User user);
    
    Ticket getUserTicket(User user);

}
