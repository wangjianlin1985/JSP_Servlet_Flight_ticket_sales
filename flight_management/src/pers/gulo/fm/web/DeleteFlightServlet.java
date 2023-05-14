// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.service.impl.AdminServiceImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DeleteFlightServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int flightNo = Integer.parseInt(request.getParameter("flightNo"));
        final AdminService aService = new AdminServiceImpl();
        final Flight flight = new Flight();
        flight.setNo(flightNo);
        try {
            aService.deleteFlight(flight);
        }
        catch (FMException e) {
            request.getSession().setAttribute("flightManageMsg", (Object)e.getMessage());
            e.printStackTrace();
            return;
        }
        finally {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/flightManage.jsp");
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/flightManage.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
