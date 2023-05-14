// 
// 
// 

package pers.gulo.fm.utils;

import java.sql.SQLException;
import java.sql.Connection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class DaoUtils
{
    private static DataSource source;
    
    static {
        DaoUtils.source = (DataSource)new ComboPooledDataSource();
    }
    
    public static DataSource getSource() {
        return DaoUtils.source;
    }
    
    public static Connection getConn() {
        try {
            return DaoUtils.source.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
