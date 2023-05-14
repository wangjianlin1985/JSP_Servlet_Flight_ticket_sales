// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.service.impl.AdminServiceImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class AddAirPlaneServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("airPlaneManageMsg");
        final AdminService aService = new AdminServiceImpl();
        final AirPlane airPlane = new AirPlane();
        airPlane.setModel(new String(request.getParameter("model").getBytes("ISO-8859-1"), "UTF-8"));
        airPlane.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        try {
            aService.addAirPlane(airPlane);
        }
        catch (FMException e) {
            request.getSession().setAttribute("airPlaneManageMsg", (Object)e.getMessage());
            e.printStackTrace();
            return;
        }
        finally {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/airPlaneManage.jsp");
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/airPlaneManage.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
