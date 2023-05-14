// 
// 
// 

package pers.gulo.fm.test;

import java.sql.SQLException;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.dao.AdminDao;
import pers.gulo.fm.dao.impl.AdminDaoImpl;

public class Main
{
    public static void main(final String[] args) throws SQLException {
        final AdminDao aDao = new AdminDaoImpl();
        final Statistic makeStatistic = aDao.makeStatistic("\u5317\u4eac");
        System.out.println(makeStatistic);
    }
}
