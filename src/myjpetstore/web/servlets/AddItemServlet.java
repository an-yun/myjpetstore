package myjpetstore.web.servlets;

import myjpetstore.domain.Cart;
import myjpetstore.domain.Item;
import myjpetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zuo on 2015/5/1.
 */
public class AddItemServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";   //��������֮�����תҳ��
    private String workingItemId;
    private CatalogService catalogService;
    private Cart cart;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");
        HttpSession session = request.getSession();
        cart =(Cart)session.getAttribute("cart");
        if(cart == null)
        {
            cart = new Cart();
        }
        if (cart.containsItemId(workingItemId)) {//����Ѿ���������������
            cart.incrementQuantityByItemId(workingItemId);
        } else {                                  //�������Ļ������item��
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
