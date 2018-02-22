package techit.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import techit.model.Unit;
import techit.model.User;
import techit.model.dao.UnitDao;

@Repository
public class UnitDaoImpl implements UnitDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Unit> getUnits() {
		
		return entityManager.createQuery( "from Ticket order by id", Unit.class )
	            .getResultList();
	}

	@Override
	public Unit getUnit(Long id) {
		return entityManager.find(Unit.class, id);
	}

	@Override
	public List<User> getSupervisors(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getTechnicians(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unit saveUnit(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

}
