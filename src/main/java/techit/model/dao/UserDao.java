package techit.model.dao;

import java.util.List;
import java.util.Set;

import techit.model.Ticket;
import techit.model.Unit;
import techit.model.User;

public interface UserDao {

    User getUser( Long id );
    
    User getUserByUsername(String username);
    
    List<User> getSupervisors(Unit unit);
	
	List<User> getTechnicians(Unit unit);

	
	Set<User> getTechniciansAssigned(Ticket ticket);
    
    User authenticateUser(String username, String password);
    
    List<User> getUsers();
    
    User saveUser( User user );

	List<User> getTechniciansAndSupervisors(Unit unit);
    

}
