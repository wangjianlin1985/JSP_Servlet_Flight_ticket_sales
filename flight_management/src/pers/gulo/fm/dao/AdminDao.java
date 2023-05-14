// 
// 
// 

package pers.gulo.fm.dao;

import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.AirPlane;
import java.util.List;

public interface AdminDao
{
    List<AirPlane> queryAllAirPlane();
    
    void insertAirPlane(AirPlane p0) throws FMException;
    
    void deleteAirPlane(AirPlane p0) throws FMException;
    
    void insertFlight(Flight p0) throws FMException;
    
    void updateFlight(Flight p0) throws FMException;
    
    List<User> queryUsers();
    
    void deleteFlight(Flight p0) throws FMException;
    
    List<Flight> queryAllFlight();
    
    void deleteUser(User p0) throws FMException;
    
    void updateUser(User p0) throws FMException;
    
    List<Order> queryAllOrder();
    
    Statistic makeStatistic();
    
    Statistic makeStatistic(String p0);
}
