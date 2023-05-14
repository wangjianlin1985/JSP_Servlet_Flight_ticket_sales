// 
// 
// 

package pers.gulo.fm.dao.resultSetHandler;

import java.sql.SQLException;
import java.sql.ResultSet;
import org.apache.commons.dbutils.ResultSetHandler;

public class CountResultSetHandler implements ResultSetHandler<Integer>
{
    public Integer handle(final ResultSet rs) throws SQLException {
        rs.next();
        return rs.getInt(1);
    }
}
