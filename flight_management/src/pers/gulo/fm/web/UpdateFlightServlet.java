// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.impl.AdminServiceImpl;
import java.sql.Timestamp;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UpdateFlightServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("flightManageMsg");
        final Flight flight = new Flight();
        final AirPlane airPlane = new AirPlane();
        flight.setNo(Integer.parseInt(request.getParameter("no")));
        airPlane.setNo(Integer.parseInt(request.getParameter("aNo")));
        flight.setAirPlane(airPlane);
        flight.setStart(new String(request.getParameter("start").getBytes("ISO-8859-1"), "UTF-8"));
        flight.setDist(new String(request.getParameter("dist").getBytes("ISO-8859-1"), "UTF-8"));
        flight.setPrice(Float.parseFloat(request.getParameter("price")));
        flight.setTime(Timestamp.valueOf(request.getParameter("time")));
        final AdminService aService = new AdminServiceImpl();
        try {
            aService.updateFlight(flight);
        }
        catch (FMException e) {
            request.getSession().setAttribute("flightManageMsg", (Object)e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/flightManage.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
