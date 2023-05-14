// 
// 
// 

package pers.gulo.fm.dao.impl;

import pers.gulo.fm.exception.FMException;
import java.sql.SQLException;
import org.apache.commons.dbutils.ResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.UserResultSetHandler;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import pers.gulo.fm.utils.DaoUtils;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.dao.UserDao;

public class UserDaoImpl implements UserDao
{
    private static final String CHECK_LOGIN_SQL = "select * from T_USER where U_USERNAME=? and U_PASSWORD=?";
    private static final String INSERT_USER_SQL = "insert into T_USER values (null,?,?,?,?,0,0)";
    private static final String RECHARGE_SQL = "update T_USER set U_BALANCE =U_BALANCE + ? where U_NO=?";
    
    @Override
    public User login(final String username, final String password) {
        User user = null;
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            final List<User> list = (List<User>)runner.query("select * from T_USER where U_USERNAME=? and U_PASSWORD=?", (ResultSetHandler)new UserResultSetHandler(), new Object[] { username, password });
            if (list != null && list.size() != 0) {
                user = list.get(0);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    @Override
    public void insertUser(final User user) throws FMException {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("insert into T_USER values (null,?,?,?,?,0,0)", new Object[] { user.getUsername(), user.getPassword(), user.getNickname(), user.getID() });
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new FMException("\u53d1\u751f\u9519\u8bef\u6216\u7528\u6237\u540d\u88ab\u5360\u7528\uff01\u8bf7\u91cd\u65b0\u6ce8\u518c\uff01");
        }
    }
    
    @Override
    public void reCharge(final User user, final float number) {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("update T_USER set U_BALANCE =U_BALANCE + ? where U_NO=?", new Object[] { number, user.getNo() });
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteUser(final int id) {
        final QueryRunner runner = new QueryRunner(DaoUtils.getSource());
        try {
            runner.update("DELETE FROM t_order WHERE O_U_NO = ?", (Object)id);
            runner.update("delete from T_USER where U_NO=?", (Object)id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
