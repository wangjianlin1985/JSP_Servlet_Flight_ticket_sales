// 
// 
// 

package pers.gulo.fm.service;

import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.User;

public interface UserService
{
    User login(String p0, String p1);
    
    void addUser(User p0) throws FMException;
    
    void recharge(User p0, float p1);
    
    void deleteUser(int p0);
}
