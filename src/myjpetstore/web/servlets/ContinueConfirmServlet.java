package myjpetstore.web.servlets;



import myjpetstore.domain.Order;
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
public class ContinueConfirmServlet extends HttpServlet {
    private static final String CONFIRM_ORDER ="/WEB-INF/jsp/order/ConfirmOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        Order order = (Order)session.getAttribute("order");
        orderService.insertOrder(order);
        session.setAttribute("orderMsg","¡¤Thank you, your order has been submitted.");
        session.setAttribute("cart",null);
        request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
    }
}
