// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.dao.impl.PassengerDaoImpl;
import pers.gulo.fm.domain.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class BookServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final User user = (User)request.getSession().getAttribute("user");
        final PassengerDao pDao = new PassengerDaoImpl();
        final int flightNo = Integer.parseInt(request.getParameter("flightNo"));
        final Flight flight = new Flight();
        flight.setNo(flightNo);
        try {
            pDao.bookFlight(user, flight);
            request.getSession().setAttribute("bookFlightMsg", (Object)"\u9884\u8ba2\u6210\u529f\uff01");
        }
        catch (FMException e) {
            request.getSession().setAttribute("bookFlightMsg", (Object)"\u9884\u8ba2\u5931\u8d25\uff01\u4f59\u7968\u4e0d\u8db3\uff01");
            e.printStackTrace();
            return;
        }
        finally {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/bookFlight.jsp");
            
        }
        //response.sendRedirect(String.valueOf(request.getContextPath()) + "/bookFlight.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
