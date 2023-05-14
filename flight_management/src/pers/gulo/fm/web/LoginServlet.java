// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.UserService;
import javax.servlet.http.HttpSession;
import pers.gulo.fm.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LoginServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final UserService uService = new UserServiceImpl();
        final User user = uService.login(username, password);
        if (user != null) {
            session.setAttribute("user", (Object)user);
            session.removeAttribute("loginMsg");
            if (user.getType() == 1) {
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/manage.jsp");
            }
            else {
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/user.jsp");
            }
        }
        else {
            request.getSession().setAttribute("loginMsg", (Object)"\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef\uff01");
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/login.jsp");
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
