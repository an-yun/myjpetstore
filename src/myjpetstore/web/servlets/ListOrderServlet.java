package myjpetstore.web.servlets;


import myjpetstore.domain.Account;
import myjpetstore.domain.Order;
import myjpetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zuo on 2015/5/2.
 */
public class ListOrderServlet extends HttpServlet {
    private static final String LIST_ORDER ="/WEB-INF/jsp/order/ListOrders.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        Account account = (Account)session.getAttribute("account");
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList",orderList);
        request.getRequestDispatcher(LIST_ORDER).forward(request,response);
    }
}
