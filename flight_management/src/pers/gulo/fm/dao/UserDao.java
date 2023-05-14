// 
// 
// 

package pers.gulo.fm.dao;

import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.User;

public interface UserDao
{
    User login(String p0, String p1);
    
    void insertUser(User p0) throws FMException;
    
    void reCharge(User p0, float p1);
    
    void deleteUser(int p0);
}
