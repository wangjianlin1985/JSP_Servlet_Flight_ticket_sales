// 
// 
// 

package pers.gulo.fm.dao.resultSetHandler;

import java.sql.SQLException;
import java.sql.ResultSet;
import org.apache.commons.dbutils.ResultSetHandler;

public class FloatResultSetHandler implements ResultSetHandler<Float>
{
    public Float handle(final ResultSet rs) throws SQLException {
        Float result = 0.0f;
        while (rs.next()) {
            result = rs.getFloat(1);
        }
        return result;
    }
}
