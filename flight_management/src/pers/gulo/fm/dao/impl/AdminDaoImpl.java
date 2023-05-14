// 
// 
// 

package pers.gulo.fm.dao.impl;

import pers.gulo.fm.dao.resultSetHandler.FloatResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.CountResultSetHandler;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.dao.resultSetHandler.OrderResultSetHandler;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.dao.resultSetHandler.AirPlaneResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FlightResultSetHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.UserResultSetHandler;
import pers.gulo.fm.domain.User;
import java.util.List;
import pers.gulo.fm.domain.Flight;
import java.sql.SQLException;
import pers.gulo.fm.exception.FMException;
import org.apache.commons.dbutils.QueryRunner;
import pers.gulo.fm.utils.DaoUtils;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.dao.AdminDao;

public class AdminDaoImpl implements AdminDao
{
    private static final String INSERT_AIRPLANE_SQL = "insert into T_AIRPLANE values (null,?,?)";
    private static final String DELETE_AIRPLANE_SQL = "delete from T_AIRPLANE where A_NO=?";
    private static final String INSERT_FLIGHT_SQL = "insert into T_FLIGHT values(null,?,?,?,?,?,0)";
    private static final String UPDATE_FLIGHT_SQL = "update T_FLIGHT set F_A_NO=?,F_START=?,F_DIST=?,F_PRICE=?,F_TIME=? where F_NO = ?";
    private static final String QUERY_USERS = "select * from T_USER";
    private static final String DELETE_FLIGHT = "delete from T_FLIGHT where F_NO = ?";
    private static final String QUERY_ALL_FLIGHT = "select * from T_FLIGHT f,T_AIRPLANE a where a.A_NO=f.F_A_NO";
    private static final String QUERY_ALL_AIRPLANE = "select * from T_AIRPLANE";
    private static final String DELETE_USER_SQL = "delete from T_USER where U_NO=?";
    private static final String UPDATE_USER_SQL = "update T_USER set U_NICKNAME=? ,U_ID=? where U_NO =?";
    private static final String QUERY_ALL_ORDER = "select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO";
    private static final String QUERY_FLIGHT_NUM_WEEK_SQL = "select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(F_TIME)";
    private static final String QUERY_FLIGHT_NUM_MONTH_SQL = "select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(F_TIME)";
    private static final String QUERY_FLIGHT_NUM_SQL = "select count(*) from T_FLIGHT";
    private static final String QUERY_ORDER_NUM_WEEK_SQL = "select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)";
    private static final String QUERY_ORDER_NUM_MONTH_SQL = "select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)";
    private static final String QUERY_ORDER_NUM_SQL = "select count(*) from T_ORDER";
    private static final String QUERY_INCOME_WEEK_SQL = "select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)";
    private static final String QUERY_INCOME_MONTH_SQL = "select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)";
    private static final String QUERY_INCOME_SQL = "select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0";
    
    @Override
    public void insertAirPlane(final AirPlane airPlane) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("insert into T_AIRPLANE values (null,?,?)", new Object[] { airPlane.getModel(), airPlane.getCapacity() });
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u63d2\u5165\u4fe1\u606f\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public void deleteAirPlane(final AirPlane airPlane) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("delete from T_AIRPLANE where A_NO=?", (Object)airPlane.getNo());
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u5916\u952e\u7ea6\u675f\uff01\u5220\u9664\u73ed\u673a\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public void insertFlight(final Flight flight) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("insert into T_FLIGHT values(null,?,?,?,?,?,0)", new Object[] { flight.getAirPlane().getNo(), flight.getStart(), flight.getDist(), flight.getPrice(), flight.getTime() });
            System.out.println(flight);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u6dfb\u52a0\u822a\u73ed\u5931\u8d25\uff01 \u6ca1\u6709\u8be5\u73ed\u673a\uff01");
        }
    }
    
    @Override
    public void updateFlight(final Flight flight) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("update T_FLIGHT set F_A_NO=?,F_START=?,F_DIST=?,F_PRICE=?,F_TIME=? where F_NO = ?", new Object[] { flight.getAirPlane().getNo(), flight.getStart(), flight.getDist(), flight.getPrice(), flight.getTime(), flight.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u4fee\u6539\u822a\u73ed\u4fe1\u606f\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public List<User> queryUsers() {
        List<User> users = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            users = (List<User>)runner.query("select * from T_USER", (ResultSetHandler)new UserResultSetHandler());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    @Override
    public void deleteFlight(final Flight flight) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("delete from T_FLIGHT where F_NO = ?", (Object)flight.getNo());
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u5916\u952e\u7ea6\u675f\uff0c\u4e0d\u80fd\u5220\u9664");
        }
    }
    
    @Override
    public List<Flight> queryAllFlight() {
        List<Flight> flights = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            flights = (List<Flight>)runner.query("select * from T_FLIGHT f,T_AIRPLANE a where a.A_NO=f.F_A_NO", (ResultSetHandler)new FlightResultSetHandler());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
    
    @Override
    public List<AirPlane> queryAllAirPlane() {
        List<AirPlane> airPlanes = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            airPlanes = (List<AirPlane>)runner.query("select * from T_AIRPLANE", (ResultSetHandler)new AirPlaneResultSetHandler());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return airPlanes;
    }
    
    @Override
    public void deleteUser(final User user) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("delete from T_USER where U_NO=?", (Object)user.getNo());
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u7531\u4e8e\u5916\u952e\u7ea6\u675f\uff0c\u5220\u9664\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public void updateUser(final User user) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("update T_USER set U_NICKNAME=? ,U_ID=? where U_NO =?", new Object[] { user.getNickname(), user.getID(), user.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u4fee\u6539\u7528\u6237\u4fe1\u606f\u5931\u8d25\uff01");
        }
    }
    
    @Override
    public List<Order> queryAllOrder() {
        List<Order> orderList = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            orderList = (List<Order>)runner.query("select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO", (ResultSetHandler)new OrderResultSetHandler());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
    @Override
    public Statistic makeStatistic() {
        final Statistic statistic = new Statistic();
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            statistic.setWeekFlight((int)runner.query("select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(F_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setMonthFlight((int)runner.query("select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(F_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setTotalFlight((int)runner.query("select count(*) from T_FLIGHT", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setWeekOrder((int)runner.query("select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setMonthOrder((int)runner.query("select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setTotalOrder((int)runner.query("select count(*) from T_ORDER", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setWeekIncome((float)runner.query("select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)", (ResultSetHandler)new FloatResultSetHandler()));
            statistic.setMonthIncome((float)runner.query("select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)", (ResultSetHandler)new FloatResultSetHandler()));
            statistic.setTotalIncome((float)runner.query("select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0", (ResultSetHandler)new FloatResultSetHandler()));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return statistic;
    }
    
    @Override
    public Statistic makeStatistic(final String start) {
        final Statistic statistic = new Statistic();
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            statistic.setWeekFlight((int)runner.query("select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(F_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setMonthFlight((int)runner.query("select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(F_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setTotalFlight((int)runner.query("select count(*) from T_FLIGHT where F_START ='" + start + "'", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setWeekOrder((int)runner.query("select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setMonthOrder((int)runner.query("select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setTotalOrder((int)runner.query("select count(*) from T_ORDER WHERE O_F_NO IN (SELECT F_NO FROM t_flight WHERE F_START='" + start + "');", (ResultSetHandler)new CountResultSetHandler()));
            statistic.setWeekIncome((float)runner.query("select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)", (ResultSetHandler)new FloatResultSetHandler()));
            statistic.setMonthIncome((float)runner.query("select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)", (ResultSetHandler)new FloatResultSetHandler()));
            statistic.setTotalIncome((float)runner.query("select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 AND f.F_START='" + start + "'", (ResultSetHandler)new FloatResultSetHandler()));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return statistic;
    }
}
