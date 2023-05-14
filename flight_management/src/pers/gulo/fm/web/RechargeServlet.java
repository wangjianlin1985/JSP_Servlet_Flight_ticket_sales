// 
// 
// 

package pers.gulo.fm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import pers.gulo.fm.service.UserService;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class RechargeServlet extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final UserService uService = new UserServiceImpl();
        final User user = new User();
        user.setNo(Integer.parseInt(request.getParameter("userNo")));
        final float number = Float.parseFloat(request.getParameter("number"));
        uService.recharge(user, number);
        final User sessionUser = (User)request.getSession().getAttribute("user");
        sessionUser.setBalance(sessionUser.getBalance() + number);
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/myWallet.jsp");
        request.getSession().setAttribute("myWalletMsg", (Object)"\u5145\u503c\u6210\u529f\uff01");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
