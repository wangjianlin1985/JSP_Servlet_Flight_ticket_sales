// 
// 
// 

package pers.gulo.fm.dao.resultSetHandler;

import java.sql.SQLException;
import pers.gulo.fm.domain.AirPlane;
import java.util.ArrayList;
import java.sql.ResultSet;
import pers.gulo.fm.domain.Flight;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;

public class FlightResultSetHandler implements ResultSetHandler<List<Flight>>
{
    public List<Flight> handle(final ResultSet rs) throws SQLException {
        final List<Flight> flightList = new ArrayList<Flight>();
        while (rs.next()) {
            final Flight flight = new Flight();
            flight.setNo(rs.getInt("F_NO"));
            flight.setStart(rs.getString("F_START"));
            flight.setDist(rs.getString("F_DIST"));
            flight.setPassengerNumber(rs.getInt("F_PSG_NUM"));
            flight.setPrice(rs.getFloat("F_PRICE"));
            flight.setTime(rs.getTimestamp("F_TIME"));
            final AirPlane airPlane = new AirPlane();
            airPlane.setNo(rs.getInt("A_NO"));
            airPlane.setModel(rs.getString("A_MODEL"));
            airPlane.setCapacity(rs.getInt("A_CAPACITY"));
            flight.setAirPlane(airPlane);
            flightList.add(flight);
        }
        return flightList;
    }
}
