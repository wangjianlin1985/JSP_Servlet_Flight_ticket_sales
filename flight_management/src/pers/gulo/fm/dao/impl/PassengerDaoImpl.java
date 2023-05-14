// 
// 
// 

package pers.gulo.fm.dao.impl;

import java.util.ArrayList;
import pers.gulo.fm.dao.resultSetHandler.OrderResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FloatResultSetHandler;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.exception.FMException;
import java.util.Date;
import pers.gulo.fm.domain.User;
import java.sql.SQLException;
import org.apache.commons.dbutils.ResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FlightResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import pers.gulo.fm.utils.DaoUtils;
import pers.gulo.fm.domain.Flight;
import java.util.List;
import pers.gulo.fm.dao.PassengerDao;

public class PassengerDaoImpl implements PassengerDao
{
    private static final String FIND_FLIGHT_SQL = "select * from T_FLIGHT f,T_AIRPLANE a where f.F_A_NO=a.A_NO";
    private static final String BOOK_FLIGHT_SQL = "insert into T_ORDER values(null,?,?,0,0,?)";
    private static final String INSERT_ORDER = "insert into T_ORDER values (null,?,?,0,0,?)";
    private static final String UPDATE_PSG_NUM_SQL = "update T_FLIGHT set F_PSG_NUM = F_PSG_NUM + 1 where F_NO = ?";
    private static final String BOUNCE_SQL = "update T_ORDER set O_IS_CANCELED = 1 where O_NO = ?";
    private static final String BOUNCE_PSG_NUM_SQL = "update T_FLIGHT set F_PSG_NUM = F_PSG_NUM -1 where F_NO = ?";
    private static final String REFUND_SQL = "update T_USER set U_BALANCE = U_BALANCE + ? * (select O_IS_PAYED from T_ORDER where O_NO= ?) where U_NO = ?";
    private static final String UPDATE_IS_PAYED_SQL = "update T_ORDER set O_IS_PAYED = 1 where O_NO = ?";
    private static final String PAY_SQL = "update T_USER set U_BALANCE = U_BALANCE - ? where U_NO = ?";
    private static final String GET_ORDER_LIST_SQL = "select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_U_NO = ?";
    private static final String GET_PAYED_ORDER_LIST_SQL = "select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_IS_PAYED=1 and O_U_NO = ?";
    private static final String GET_UNPAYED_ORDER_LIST_SQL = "select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_IS_PAYED=0 and O_U_NO = ?";
    private static final String GET_UNBOOKED_FLIGHT = "select * from T_FLIGHT f,T_AIRPLANE a where f.F_A_NO=a.A_NO and f.F_NO not in (select O_F_NO from T_ORDER o where o.O_U_NO = ?)";
    private static final String GET_ORDER_SQL = "select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_NO = ?";
    private static final String QUERY_BALANCE = "select U_BALANCE from T_USER where U_NO = ?";
    
    @Override
    public List<Flight> findFlightByStartAndDist(final String start, final String dist) {
        List<Flight> flightList = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        String SQL = new String("select * from T_FLIGHT f,T_AIRPLANE a where f.F_A_NO=a.A_NO");
        if (start != null && !start.trim().equals("")) {
            SQL = String.valueOf(SQL) + " and F_START='" + start.trim() + "'";
        }
        if (dist != null && !dist.trim().equals("")) {
            SQL = String.valueOf(SQL) + " and F_DIST='" + dist + "'";
        }
        System.out.println(SQL);
        try {
            flightList = (List<Flight>)runner.query(SQL, (ResultSetHandler)new FlightResultSetHandler());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flightList;
    }
    
    @Override
    public void bookFlight(final User user, final Flight flight) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("insert into T_ORDER values(null,?,?,0,0,?)", new Object[] { user.getNo(), flight.getNo(), new Date() });
            runner.update("update T_FLIGHT set F_PSG_NUM = F_PSG_NUM + 1 where F_NO = ?", (Object)flight.getNo());
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u9884\u8ba2\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public void bounce(final Order order) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("update T_ORDER set O_IS_CANCELED = 1 where O_NO = ?", (Object)order.getNo());
            runner.update("update T_FLIGHT set F_PSG_NUM = F_PSG_NUM -1 where F_NO = ?", (Object)order.getFlight().getNo());
            if (order.getIsPayed()) {
                runner.update("update T_USER set U_BALANCE = U_BALANCE + ? * (select O_IS_PAYED from T_ORDER where O_NO= ?) where U_NO = ?", new Object[] { order.getFlight().getPrice(), order.getNo(), order.getUser().getNo() });
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u9000\u7968\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public void payFlight(final Order order) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            final Float balance = (Float)runner.query("select U_BALANCE from T_USER where U_NO = ?", (ResultSetHandler)new FloatResultSetHandler(), new Object[] { order.getUser().getNo() });
            if (balance < order.getFlight().getPrice()) {
                throw new FMException("\u4ed8\u6b3e\u5931\u8d25\uff01\u4f59\u989d\u4e0d\u8db3\uff01");
            }
            runner.update("update T_ORDER set O_IS_PAYED = 1 where O_NO = ?", (Object)order.getNo());
            runner.update("update T_USER set U_BALANCE = U_BALANCE - ? where U_NO = ?", new Object[] { order.getFlight().getPrice(), order.getUser().getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u4ed8\u6b3e\u5931\u8d25\uff01\u4f59\u989d\u4e0d\u8db3\uff01");
        }
    }
    
    @Override
    public List<Order> getOrderListOfUser(final User user) {
        List<Order> orderList = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            orderList = (List<Order>)runner.query("select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_U_NO = ?", (ResultSetHandler)new OrderResultSetHandler(), new Object[] { user.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
    @Override
    public List<Order> getPayedOrderListOfUser(final User user) {
        List<Order> orderList = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            orderList = (List<Order>)runner.query("select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_IS_PAYED=1 and O_U_NO = ?", (ResultSetHandler)new OrderResultSetHandler(), new Object[] { user.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
    @Override
    public List<Order> getUnPayedOrderListOfUser(final User user) {
        List<Order> orderList = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            orderList = (List<Order>)runner.query("select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_IS_PAYED=0 and O_U_NO = ?", (ResultSetHandler)new OrderResultSetHandler(), new Object[] { user.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
    @Override
    public List<Flight> getUnBookedFlight(final User user) {
        List<Flight> unBookedFlightList = new ArrayList<Flight>();
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            System.err.println(String.valueOf(user.getNo()) + "---------------------------");
            unBookedFlightList = (List<Flight>)runner.query("select * from T_FLIGHT f,T_AIRPLANE a where f.F_A_NO=a.A_NO and f.F_NO not in (select O_F_NO from T_ORDER o where o.O_U_NO = ?)", (ResultSetHandler)new FlightResultSetHandler(), new Object[] { user.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return unBookedFlightList;
    }
    
    @Override
    public Order getOrder(final Order order) {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        Order o = new Order();
        try {
            final List<Order> list = (List<Order>)runner.query("select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_NO = ?", (ResultSetHandler)new OrderResultSetHandler(), new Object[] { order.getNo() });
            o = list.get(0);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }
}
