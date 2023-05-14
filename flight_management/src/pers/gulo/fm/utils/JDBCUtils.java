// 
// 
// 

package pers.gulo.fm.utils;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.Reader;
import java.io.FileReader;
import java.util.Properties;

public class JDBCUtils
{
    private static Properties prop;
    
    static {
        JDBCUtils.prop = null;
        try {
            (JDBCUtils.prop = new Properties()).load(new FileReader(JDBCUtils.class.getClassLoader().getResource("config.properties").getPath()));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(JDBCUtils.prop.getProperty("driver"));
        return DriverManager.getConnection(JDBCUtils.prop.getProperty("url"), JDBCUtils.prop.getProperty("user"), JDBCUtils.prop.getProperty("password"));
    }
    
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        Label_0032: {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    break Label_0032;
                }
                finally {
                    rs = null;
                }
                rs = null;
            }
        }
        Label_0064: {
            if (stat != null) {
                try {
                    stat.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    break Label_0064;
                }
                finally {
                    stat = null;
                }
                stat = null;
            }
        }
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            finally {
                conn = null;
            }
            conn = null;
        }
    }
}
