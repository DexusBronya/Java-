package eems.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;



import org.omg.CORBA.PUBLIC_MEMBER;

import eems.dao.EeDao;
import eems.pojo.Ee;

public class TestEeDao {

	@Test
	public void test() {
		EeDao dao = new EeDao();
		try {
			dao.updateAttendance("00004","上班");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
		
	@Test
	public void test1(){
		EeDao dao = new EeDao();
		String timeBack = " 00:00:00";
		String time = "2024-3-24"; //xxxx-xx-xx
		try {
			String result = dao.getAttendanceStatusByEnumber("00001", time+timeBack);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}


}