package techit.model.dao;

import java.util.List;

import techit.model.Unit;
import techit.model.User;

public interface UnitDao {
	
	Unit getUnit(Long id);
	
	List<Unit> getUnits();
	
	Unit saveUnit(Unit unit);
	
	Unit getUnitByName(String name);
	
	void deleteUnit(Unit unit);
	
	void addTechnicianToUnit(User technician, Unit unit);
	
	void removeTechnicianFromUnit(User technician, Unit unit);
	

}
