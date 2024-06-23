package eems.impl;

import eems.dao.EeDao;
import eems.pojo.Ee;
import eems.service.EeService;

import java.sql.SQLException;


public class EeServiceImpl implements EeService {

    private EeDao eeDao;

    public EeServiceImpl() {
        this.eeDao = new EeDao();
    }

    @Override
    public void updateAttendance(String enumber,String time) throws SQLException {
        eeDao.updateAttendance(enumber, time);
    }

    @Override
    public String getAttendanceStatusByEnumber(String enumber, String date) throws SQLException {
        String status = eeDao.getAttendanceStatusByEnumber(enumber, date);
        // 在这里可以根据需要对考勤状态进行处理
        return status;
    }



	@Override
	public Ee login(String username, String password) throws Exception {
		Ee user = eeDao.findUser(username, password);
		if(null == user){
			return null;
		} else{
			return user;
		}
	}


}
