package techit.model.dao;

import java.util.List;

import techit.model.Unit;
import techit.model.User;

public interface UnitDao {
	
	Unit getUnit(Long id);
	
	List<Unit> getUnits();
	
	List<User> getSupervisors(Unit unit);
	
	List<User> getTechnicians(Unit unit);
	
	Unit saveUnit(Unit unit);

}
