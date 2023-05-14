// 
// 
// 

package pers.gulo.fm.service.impl;

import java.io.UnsupportedEncodingException;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.dao.AdminDao;
import pers.gulo.fm.dao.impl.AdminDaoImpl;
import pers.gulo.fm.domain.User;
import java.util.List;
import pers.gulo.fm.service.AdminService;

public class AdminServiceImpl implements AdminService
{
    @Override
    public List<User> listAllUser() {
        final AdminDao aDao = new AdminDaoImpl();
        final List<User> queryUsers = aDao.queryUsers();
        return queryUsers;
    }
    
    @Override
    public void deleteUser(final User user) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.deleteUser(user);
    }
    
    @Override
    public void updateUser(final User user) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.updateUser(user);
    }
    
    @Override
    public List<Flight> listAllFlight() {
        final AdminDao aDao = new AdminDaoImpl();
        return aDao.queryAllFlight();
    }
    
    @Override
    public void updateFlight(final Flight flight) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.updateFlight(flight);
    }
    
    @Override
    public void addFlight(final Flight flight) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.insertFlight(flight);
    }
    
    @Override
    public void deleteFlight(final Flight flight) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.deleteFlight(flight);
    }
    
    @Override
    public List<AirPlane> listAllAirPlane() {
        final AdminDao aDao = new AdminDaoImpl();
        final List<AirPlane> queryAllAirPlane = aDao.queryAllAirPlane();
        return queryAllAirPlane;
    }
    
    @Override
    public void addAirPlane(final AirPlane airPlane) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.insertAirPlane(airPlane);
    }
    
    @Override
    public void deleteAirPlane(final AirPlane airPlane) throws FMException {
        final AdminDao aDao = new AdminDaoImpl();
        aDao.deleteAirPlane(airPlane);
    }
    
    @Override
    public List<Order> listAllOrder() {
        final AdminDao aDao = new AdminDaoImpl();
        return aDao.queryAllOrder();
    }
    
    @Override
    public Statistic makeStatistic() {
        final AdminDao aDao = new AdminDaoImpl();
        return aDao.makeStatistic();
    }
    
    @Override
    public Statistic makeStatistic(String start) {
        try {
            start = new String(start.getBytes("ISO-8859-1"), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ("".equals(start)) {
            final AdminDao aDao = new AdminDaoImpl();
            return aDao.makeStatistic();
        }
        final AdminDao aDao = new AdminDaoImpl();
        return aDao.makeStatistic(start);
    }
}
