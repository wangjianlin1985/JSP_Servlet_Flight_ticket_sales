// 
// 
// 

package pers.gulo.fm.dao.resultSetHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import pers.gulo.fm.domain.AirPlane;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;

public class AirPlaneResultSetHandler implements ResultSetHandler<List<AirPlane>>
{
    public List<AirPlane> handle(final ResultSet rs) throws SQLException {
        final List<AirPlane> airPlanes = new ArrayList<AirPlane>();
        while (rs.next()) {
            final AirPlane airPlane = new AirPlane();
            airPlane.setNo(rs.getInt("A_NO"));
            airPlane.setModel(rs.getString("A_MODEL"));
            airPlane.setCapacity(rs.getInt("A_CAPACITY"));
            airPlanes.add(airPlane);
        }
        return airPlanes;
    }
}
