// 
// 
// 

package pers.gulo.fm.dao;

import pers.gulo.fm.domain.Order;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.domain.Flight;
import java.util.List;

public interface PassengerDao
{
    List<Flight> findFlightByStartAndDist(String p0, String p1);
    
    void bookFlight(User p0, Flight p1) throws FMException;
    
    void bounce(Order p0) throws FMException;
    
    void payFlight(Order p0) throws FMException;
    
    List<Order> getOrderListOfUser(User p0);
    
    List<Order> getPayedOrderListOfUser(User p0);
    
    List<Order> getUnPayedOrderListOfUser(User p0);
    
    List<Flight> getUnBookedFlight(User p0);
    
    Order getOrder(Order p0);
}
