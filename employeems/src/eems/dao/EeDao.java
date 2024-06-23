package eems.dao;

import eems.pojo.Ee;
import eems.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EeDao {

    // 获取当日信息并更新打卡状态
    public void updateAttendance(String enumber, String time) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 获取当前日期和时间
        java.util.Date now = new java.util.Date();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        java.util.Date now1 = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(now1.getTime());

        // 查询当日信息是否已存在
        conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM eecheck WHERE enumber=? AND date=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, enumber);
        pstmt.setDate(2, currentDate);
        rs = pstmt.executeQuery();

        try {
            if (!rs.next()) {
                // 当日信息不存在，插入新记录
                sql = "INSERT INTO eecheck (enumber, goonwork, gooffwork, date, dateSearch) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, enumber);

                switch (time) {
                    case "上班":
                        pstmt.setString(2, "1"); // 上班打卡设置为1
                        pstmt.setString(3, "0"); // 下班打卡设置为0
                        break;

                    case "下班":
                        pstmt.setString(2, "0"); // 上班打卡设置为0
                        pstmt.setString(3, "1"); // 下班打卡设置为1
                        break;

                    default:
                        System.out.println("请正确选择上班或者下班打卡");
                        break;
                }

                pstmt.setTimestamp(4, currentTimestamp);
                pstmt.setDate(5, currentDate);

                pstmt.executeUpdate();
                System.out.println("打卡成功！");
            } else {
                switch (time) {
                    case "上班":
                        // 当日信息已存在，更新上班打卡状态为1
                        sql = "UPDATE eecheck SET goonwork=? WHERE enumber=? AND date=?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, "1");
                        pstmt.setString(2, enumber);
                        pstmt.setTimestamp(3, currentTimestamp);
                        pstmt.executeUpdate();
                        System.out.println("上班打卡成功！");
                        break;

                    case "下班":
                        // 当日信息已存在，更新下班班打卡状态为1
                        sql = "UPDATE eecheck SET gooffwork=? WHERE enumber=? AND date=?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, "1");
                        pstmt.setString(2, enumber);
                        pstmt.setTimestamp(3, currentTimestamp);
                        pstmt.executeUpdate();
                        System.out.println("下班打卡成功！");
                        break;

                    default:
                        System.out.println("请正确选择上班或者下班打卡");
                        break;
                }
            }
        } finally {
            JDBCUtil.close(rs, pstmt, conn);
        }
    }

    // 查询某个员工在特定日期的考勤状态
    public String getAttendanceStatusByEnumber(String enumber, String date) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String status = "";

        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT goonwork, gooffwork FROM eecheck WHERE enumber=? AND dateSearch=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, enumber);
            pstmt.setString(2, date);
            rs = pstmt.executeQuery();

            int flagmoon = 0;
            int flagnight = 0;

            while (rs.next()) {
                String goonwork = rs.getString("goonwork");
                String gooffwork = rs.getString("gooffwork");

                if (goonwork.equals("1") && gooffwork.equals("0")) {
                    flagmoon = 1;
                }

                if (goonwork.equals("0") && gooffwork.equals("1")) {
                    flagnight = 1;
                }
            }

            if (flagmoon == 1 && flagnight == 0) {
                status = "上班已打卡，下班未打卡";
            } else if (flagmoon == 0 && flagnight == 1) {
                status = "上班未打卡,下班已打卡";
            } else if (flagmoon == 1 && flagnight == 1) {
                status = "全勤";
            } else if (flagmoon == 0 && flagnight == 0) {
                status = "缺勤";
            }
        } finally {
            JDBCUtil.close(rs, pstmt, conn);
        }

        return status;
    }

    public Ee findUser(String username, String password) throws Exception {
        Ee user = null;
        String sql = "select * from ee where username = ? and password = ?";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new Ee();
            user.setId(resultSet.getInt("id"));
            user.setEnumber(resultSet.getString("enumber"));
            user.setEname(resultSet.getString("ename"));
            user.setEsex(resultSet.getString("esex"));
            user.setEsalary(resultSet.getInt("esalary"));
            user.setEauthority(resultSet.getString("eauthority"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        JDBCUtil.close(resultSet, preparedStatement, connection);
        return user;
    }
}
