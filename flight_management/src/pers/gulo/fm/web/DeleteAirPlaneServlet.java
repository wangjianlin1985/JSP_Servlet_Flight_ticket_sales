// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.impl.AdminServiceImpl;
import pers.gulo.fm.domain.AirPlane;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DeleteAirPlaneServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final int no = Integer.parseInt(request.getParameter("airPlaneNo"));
        final AirPlane airPlane = new AirPlane();
        airPlane.setNo(no);
        final AdminService aService = new AdminServiceImpl();
        try {
            aService.deleteAirPlane(airPlane);
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
