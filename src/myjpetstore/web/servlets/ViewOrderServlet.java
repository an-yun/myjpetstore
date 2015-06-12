package myjpetstore.web.servlets;

import myjpetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zuo on 2015/5/2.
 */
public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER ="/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        int orderId =Integer.parseInt(request.getParameter("orderId"));
        HttpSession session = request.getSession();
        session.setAttribute("order", orderService.getOrder(orderId));
        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }
}
