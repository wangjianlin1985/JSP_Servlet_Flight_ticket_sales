// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.dao.impl.PassengerDaoImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class PayServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int orderNo = Integer.parseInt(request.getParameter("orderNo"));
        final PassengerDao pDao = new PassengerDaoImpl();
        Order order = new Order();
        order.setNo(orderNo);
        order = pDao.getOrder(order);
        try {
            pDao.payFlight(order);
            final User sessionUser = (User)request.getSession().getAttribute("user");
            sessionUser.setBalance(sessionUser.getBalance() - order.getFlight().getPrice());
            request.getSession().setAttribute("myOrderMsg", (Object)"\u4ed8\u6b3e\u6210\u529f\uff01");
        }
        catch (FMException e) {
            request.getSession().setAttribute("myOrderMsg", (Object)e.getMessage());
            e.printStackTrace();
            return;
        }
        finally {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/myOrder.jsp");
        }
        //response.sendRedirect(String.valueOf(request.getContextPath()) + "/myOrder.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
