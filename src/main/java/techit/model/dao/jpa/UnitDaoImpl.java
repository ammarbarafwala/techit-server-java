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
		
		return entityManager.createQuery( "from Unit order by id", Unit.class )
	            .getResultList();
	}

	@Override
	public Unit getUnit(Long id) {
		return entityManager.find(Unit.class, id);
	}


	@Override
	public Unit saveUnit(Unit unit) {
		return entityManager.merge(unit);
	}

	@Override
	public Unit getUnitByName(String name) {
		List<Unit> units =  entityManager.createQuery( "from Unit where name = :name ", Unit.class )
	            .setParameter("name", name).setMaxResults(1).getResultList();
		return units.size() == 0? null : units.get(0);
	}

	@Override
	public void deleteUnit(Unit unit) {
		entityManager.remove(unit);
	}

	@Override
	public void addTechnicianToUnit(User technician, Unit unit) {
		unit.getTechnicians().add(technician);
	}

	@Override
	public void removeTechnicianFromUnit(User technician, Unit unit) {
		unit.getTechnicians().remove(technician);
	}

}
