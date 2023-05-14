// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.UserService;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class AddUserServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final UserService userService = new UserServiceImpl();
        final User user = new User();
        user.setUsername(new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8"));
        user.setPassword(request.getParameter("password"));
        user.setNickname(new String(request.getParameter("nickname").getBytes("ISO-8859-1"), "UTF-8"));
        user.setID(request.getParameter("id"));
        try {
            userService.addUser(user);
            request.getSession().setAttribute("loginMsg", (Object)"\u6ce8\u518c\u6210\u529f\uff01");
        }
        catch (FMException e) {
            request.getSession().setAttribute("loginMsg", (Object)e.getMessage());
            e.printStackTrace();
            return;
        }
        finally {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/login.jsp");
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/login.jsp");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
