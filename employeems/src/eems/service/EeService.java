package eems.service;

import java.sql.SQLException;

import eems.pojo.Ee;

public interface EeService {
	void updateAttendance(String enumber,String time) throws SQLException;

	String getAttendanceStatusByEnumber(String enumber, String date) throws SQLException;
	
	Ee login(String username, String password) throws Exception;

	
}
