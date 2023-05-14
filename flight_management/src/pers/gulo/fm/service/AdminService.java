// 
// 
// 

package pers.gulo.fm.service;

import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.User;
import java.util.List;

public interface AdminService
{
    List<User> listAllUser();
    
    void deleteUser(User p0) throws FMException;
    
    void updateUser(User p0) throws FMException;
    
    List<Flight> listAllFlight();
    
    void updateFlight(Flight p0) throws FMException;
    
    void addFlight(Flight p0) throws FMException;
    
    void deleteFlight(Flight p0) throws FMException;
    
    List<AirPlane> listAllAirPlane();
    
    void addAirPlane(AirPlane p0) throws FMException;
    
    void deleteAirPlane(AirPlane p0) throws FMException;
    
    List<Order> listAllOrder();
    
    Statistic makeStatistic();
    
    Statistic makeStatistic(String p0);
}
