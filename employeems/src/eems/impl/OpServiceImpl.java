package eems.impl;

import eems.dao.OpDao;
import eems.pojo.Ee;
import eems.pojo.Eecheck;
import eems.service.OpService;

import java.sql.SQLException;
import java.util.List;

public class OpServiceImpl implements OpService {

	private OpDao opDao;

	public OpServiceImpl() {
		this.opDao = new OpDao();
	}

	@Override
	public void addEmployee(Ee employee) throws SQLException {
		opDao.addEmployee(employee);
	}

	@Override
	public int deleteEmployee(String enumber) throws SQLException {
		return opDao.deleteEmployee(enumber);
	}

	@Override
	public void updateEmployee(Ee employee) throws SQLException {
		opDao.updateEmployee(employee);
	}

	@Override
	public Ee findEmployeeByNumber(String enumber) throws SQLException {
		return opDao.findEmployeeByNumber(enumber);
	}

	@Override
	public List<Ee> findEmployeeByName(String ename) throws SQLException {
		return opDao.findEmployeeByName(ename);
	}

	@Override
	public List<Ee> findAllEmployees() throws SQLException {
		return opDao.findAllEmployees();
	}

	@Override
	public List<Eecheck> getAttendanceStatusByDate(String date)
			throws SQLException {
		return opDao.getAttendanceStatusByDate(date);
	}

	@Override
	public List<Object[]> getAttendanceCountByEnumber(String enumber, String yearMonth) throws SQLException {
	    return opDao.getAttendanceCountByEnumber(enumber, yearMonth);
	}

	@Override
	public List<Object[]> getAttendanceByEnumber(String enumber,
			String yearMonth) throws SQLException {
		// TODO 自动生成的方法存根
		return opDao.getAttendanceByEnumber(enumber, yearMonth);
	}


}
