// 
// 
// 

package pers.gulo.fm.dao.resultSetHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import pers.gulo.fm.domain.User;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;

public class UserResultSetHandler implements ResultSetHandler<List<User>>
{
    public List<User> handle(final ResultSet rs) throws SQLException {
        final List<User> userList = new ArrayList<User>();
        while (rs.next()) {
            final User user = new User();
            user.setUsername(rs.getString("U_USERNAME"));
            user.setPassword(rs.getString("U_PASSWORD"));
            user.setNo(rs.getInt("U_NO"));
            user.setID(rs.getString("U_ID"));
            user.setNickname(rs.getString("U_NICKNAME"));
            user.setType(rs.getInt("U_TYPE"));
            user.setBalance(rs.getFloat("U_BALANCE"));
            userList.add(user);
        }
        return userList;
    }
}
