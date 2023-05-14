// 
// 
// 

package pers.gulo.fm.dao.resultSetHandler;

import java.sql.SQLException;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import java.util.ArrayList;
import java.sql.ResultSet;
import pers.gulo.fm.domain.Order;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;

public class OrderResultSetHandler implements ResultSetHandler<List<Order>>
{
    public List<Order> handle(final ResultSet rs) throws SQLException {
        final List<Order> orderList = new ArrayList<Order>();
        while (rs.next()) {
            final Order order = new Order();
            order.setNo(rs.getInt("O_NO"));
            order.setTime(rs.getTimestamp("O_TIME"));
            order.setIsPayed(rs.getInt("O_IS_PAYED") == 1);
            order.setIsCanceled(rs.getInt("O_IS_CANCELED") == 1);
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
            order.setFlight(flight);
            final User user = new User();
            user.setUsername(rs.getString("U_USERNAME"));
            user.setPassword(rs.getString("U_PASSWORD"));
            user.setNo(rs.getInt("U_NO"));
            user.setID(rs.getString("U_ID"));
            user.setNickname(rs.getString("U_NICKNAME"));
            user.setType(rs.getInt("U_TYPE"));
            user.setBalance(rs.getFloat("U_BALANCE"));
            order.setUser(user);
            orderList.add(order);
        }
        return orderList;
    }
}
