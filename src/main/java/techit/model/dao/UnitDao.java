package techit.model.dao;

import java.util.List;

import techit.model.Unit;
import techit.model.User;

public interface UnitDao {
	
	List<Unit> getUnits();
	
	Unit getUnit(Long id);
	
	List<User> getSupervisors(Unit unit);
	
	List<User> getTechnicians(Unit unit);
	
	

}
