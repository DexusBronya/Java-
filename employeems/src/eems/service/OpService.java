package eems.service;

import java.sql.SQLException;
import java.util.List;

import eems.pojo.Ee;
import eems.pojo.Eecheck;

public interface OpService {
	void addEmployee(Ee employee) throws SQLException;
	
	int deleteEmployee(String enumber) throws SQLException;
	
	void updateEmployee(Ee employee) throws SQLException;
	
	Ee findEmployeeByNumber(String enumber) throws SQLException;
	
	List<Ee> findEmployeeByName(String ename) throws SQLException;
	
	List<Ee> findAllEmployees() throws SQLException;
	
	List<Eecheck> getAttendanceStatusByDate(String date) throws SQLException;

	List<Object[]> getAttendanceCountByEnumber(String enumber, String month) throws SQLException;
	
	List<Object[]> getAttendanceByEnumber(String enumber, String yearMonth) throws SQLException;
}
