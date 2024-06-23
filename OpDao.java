package eems.dao;

import eems.pojo.Ee;
import eems.pojo.Eecheck;
import eems.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpDao {

    // 添加员工
    public void addEmployee(Ee employee) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = JDBCUtil.getConnection();
        String sql = "INSERT INTO ee (ename, enumber, esex, eborn, eentry, esalary, eauthority,username,password) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employee.getEname());
        pstmt.setString(2, employee.getEnumber());
        pstmt.setString(3, employee.getEsex());
        pstmt.setString(4, employee.getEborn());
        pstmt.setString(5, employee.getEentry());
        pstmt.setInt(6, employee.getEsalary());
        pstmt.setString(7, employee.getEauthority());
        pstmt.setString(8, employee.getUsername());
        pstmt.setString(9, employee.getPassword());

        pstmt.executeUpdate();

        JDBCUtil.close(null, pstmt, conn);
    }

    // 删除员工，返回员工信息以便确认删除
    public int deleteEmployee(String enumber) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Ee employee = null;

        conn = JDBCUtil.getConnection();
        String sql = "delete  FROM ee WHERE enumber=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, enumber);
        int answer = pstmt.executeUpdate();

        if (answer == 1){
        	System.out.println("删除成功");
        }else System.out.println("删除失败");

        JDBCUtil.close(rs, pstmt, conn);

        return answer;
    }

    // 修改员工信息
    public void updateEmployee(Ee employee) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = JDBCUtil.getConnection();
        String sql = "UPDATE ee SET ename=?, esex=?, eborn=?, eentry=?, esalary=?, eauthority=? WHERE enumber=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employee.getEname());
        pstmt.setString(2, employee.getEsex());
        pstmt.setString(3, employee.getEborn());
        pstmt.setString(4, employee.getEentry());
        pstmt.setInt(5, employee.getEsalary());
        pstmt.setString(6, employee.getEauthority());
        pstmt.setString(7, employee.getEnumber());
        int answer = pstmt.executeUpdate();
        if (answer==1) System.out.println("员工信息修改成功！");
        else System.out.println("员工信息修改失败");

        JDBCUtil.close(null, pstmt, conn);
    }

    // 查询员工信息，根据工号查询
    public Ee findEmployeeByNumber(String enumber) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Ee employee = null;

        conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM ee WHERE enumber=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, enumber);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            employee = new Ee();
            employee.setId(rs.getInt("id"));
            employee.setEname(rs.getString("ename"));
            employee.setEnumber(rs.getString("enumber"));
            employee.setEsex(rs.getString("esex"));
            employee.setEborn(rs.getString("eborn"));
            employee.setEentry(rs.getString("eentry"));
            employee.setEsalary(rs.getInt("esalary"));
            employee.setEauthority(rs.getString("eauthority"));
        } else {
            System.out.println("未找到该员工信息！");
        }

        JDBCUtil.close(rs, pstmt, conn);

        return employee;
    }

    // 查询员工信息，根据姓名查询
    public List<Ee> findEmployeeByName(String ename) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Ee> employees = new ArrayList<>();

        conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM ee WHERE ename=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, ename);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Ee employee = new Ee();
            employee.setId(rs.getInt("id"));
            employee.setEname(rs.getString("ename"));
            employee.setEnumber(rs.getString("enumber"));
            employee.setEsex(rs.getString("esex"));
            employee.setEborn(rs.getString("eborn"));
            employee.setEentry(rs.getString("eentry"));
            employee.setEsalary(rs.getInt("esalary"));
            employee.setEauthority(rs.getString("eauthority"));
            employees.add(employee);
        }

        JDBCUtil.close(rs, pstmt, conn);

        return employees;
    }

    // 查询所有员工信息
    public List<Ee> findAllEmployees() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Ee> employees = new ArrayList<>();

        conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM ee";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Ee employee = new Ee();
            employee.setId(rs.getInt("id"));
            employee.setEname(rs.getString("ename"));
            employee.setEnumber(rs.getString("enumber"));
            employee.setEsex(rs.getString("esex"));
            employee.setEborn(rs.getString("eborn"));
            employee.setEentry(rs.getString("eentry"));
            employee.setEsalary(rs.getInt("esalary"));
            employee.setEauthority(rs.getString("eauthority"));
            employees.add(employee);
        }

        JDBCUtil.close(rs, pstmt, conn);

        return employees;
    }
    
    public List<Eecheck> getAttendanceStatusByDate(String date) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Eecheck> attendanceStatusList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM eecheck WHERE dateSearch=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Eecheck eecheck = new Eecheck();
                eecheck.setEnumber(rs.getString("enumber"));
                eecheck.setGoonwork(rs.getString("goonwork"));
                eecheck.setGooffwork(rs.getString("gooffwork"));
                eecheck.setDate(rs.getString("date"));
                attendanceStatusList.add(eecheck);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn);
        }

        return attendanceStatusList;
    }
    

}
