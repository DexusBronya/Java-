package eems.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Orange
 * @date 2024/3/15 -9:30
 */
public class JDBCUtil {
	private static DataSource dataSource=null;  
    static{  
        dataSource=new ComboPooledDataSource("mysql1");  
    } 
    //建立连接的方法
    public static Connection getConnection() {
        Connection connection = null;
        try { 
            connection = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    //关闭连接的方法
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection connection){
        try {
            if(null != rs){
                rs.close();
            }
            if(null != pstmt){
                pstmt.close();
            }
            if(null != connection){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

