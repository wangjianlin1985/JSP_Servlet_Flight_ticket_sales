// 
// 
// 

package pers.gulo.fm.service;

import java.util.List;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;

public interface PassengerService
{
    void bookFlight(User p0, Flight p1);
    
    void cancelBook(User p0, Flight p1);
    
    void pay(User p0, Flight p1);
    
    List<Flight> listUnBookedFlight(User p0);
}
