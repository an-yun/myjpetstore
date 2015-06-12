package myjpetstore.web.servlets;

import myjpetstore.domain.Account;
import myjpetstore.domain.Cart;
import myjpetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zuo on 2015/5/2.
 */
public class CheckOutServlet extends HttpServlet {
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String CHECK_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
        Cart cart  =(Cart)session.getAttribute("cart");
        if(account==null)
        {
            session.setAttribute("checkOut","¡¤You must sign on before attempting to check out. Please sign on and try checking out again.");
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else
        {
            Order order = new Order();
            order.initOrder(account,cart);
            session.setAttribute("order",order);
            request.getRequestDispatcher(CHECK_ORDER).forward(request, response);
        }
    }
}
