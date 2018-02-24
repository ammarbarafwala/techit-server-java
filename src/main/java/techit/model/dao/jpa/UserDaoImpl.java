package techit.model.dao.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import techit.model.Ticket;
import techit.model.Unit;
import techit.model.User;
import techit.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser( Long id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class )
            .getResultList();
    }

    @Override
    @Transactional
    public User saveUser( User user )
    {
        return entityManager.merge( user );
    }
	
	@Override
	public List<User> getSupervisors(Unit unit) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from User where unit = :unit and post = :supervisor", User.class )
		.setParameter( "unit", unit).setParameter("supervisor", User.Position.SUPERVISING_TECHNICIAN)
        .getResultList();
	}

	@Override
	public List<User> getTechnicians(Unit unit) {
		return entityManager.createQuery( "from User where unit = :unit and post = :technician", User.class )
				.setParameter( "unit", unit).setParameter("technician", User.Position.TECHNICIAN)
		        .getResultList();
	}
	
	@Override
	public List<User> getTechniciansAndSupervisors(Unit unit) {
		return entityManager.createQuery( "from User where unit = :unit and post = :technician or post = :supervisor", User.class )
				.setParameter( "unit", unit).setParameter("supervisor", User.Position.SUPERVISING_TECHNICIAN)
				.setParameter("technician", User.Position.TECHNICIAN)
		        .getResultList();
	}


	@Override
	public User getUserByUsername(String username) {
		List<User> users = entityManager.createQuery("from User where username = :username", User.class)
				 .setParameter("username", username).setMaxResults(1).getResultList();
		return users.size() == 0? null : users.get(0);
	}
	
	@Override
	public Set<User> getTechniciansAssigned(Ticket ticket) {
		return ticket.getTechnicians();
	}

	@Override
	public User authenticateUser(String username, String password) {
		User user = getUserByUsername(username);
		if (user !=null) {
			if (user.getPassword().equals(password)){
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}

}
