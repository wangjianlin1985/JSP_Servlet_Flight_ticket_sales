// 
// 
// 

package pers.gulo.fm.service.impl;

import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.dao.impl.PassengerDaoImpl;
import java.util.List;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.PassengerService;

public class PassengerServiceImpl implements PassengerService
{
    @Override
    public void bookFlight(final User user, final Flight flight) {
    }
    
    @Override
    public void cancelBook(final User user, final Flight flight) {
    }
    
    @Override
    public void pay(final User user, final Flight flight) {
    }
    
    @Override
    public List<Flight> listUnBookedFlight(final User user) {
        final PassengerDao pDao = new PassengerDaoImpl();
        return pDao.getUnBookedFlight(user);
    }
}
