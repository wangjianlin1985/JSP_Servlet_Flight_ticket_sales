// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.impl.AdminServiceImpl;
import pers.gulo.fm.domain.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UpdateUserServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("userManageMsg");
        final String nickname = new String(request.getParameter("nickname").getBytes("ISO-8859-1"), "UTF-8");
        final String id = request.getParameter("id");
        final int no = Integer.parseInt(request.getParameter("no"));
        final User user = new User();
        user.setNo(no);
        user.setNickname(nickname);
        user.setID(id);
        final AdminService aService = new AdminServiceImpl();
        try {
            aService.updateUser(user);
        }
        catch (FMException e) {
            request.getSession().setAttribute("userManageMsg", (Object)e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/userManage.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
