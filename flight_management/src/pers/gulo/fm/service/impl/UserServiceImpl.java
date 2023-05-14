// 
// 
// 

package pers.gulo.fm.service.impl;

import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.dao.UserDao;
import pers.gulo.fm.dao.impl.UserDaoImpl;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.UserService;

public class UserServiceImpl implements UserService
{
    @Override
    public User login(final String username, final String password) {
        final UserDao uDao = new UserDaoImpl();
        return uDao.login(username, password);
    }
    
    @Override
    public void addUser(final User user) throws FMException {
        final UserDao uDao = new UserDaoImpl();
        uDao.insertUser(user);
    }
    
    @Override
    public void recharge(final User user, final float number) {
        final UserDao uDao = new UserDaoImpl();
        uDao.reCharge(user, number);
    }
    
    @Override
    public void deleteUser(final int id) {
        final UserDao uDao = new UserDaoImpl();
        uDao.deleteUser(id);
    }
}
