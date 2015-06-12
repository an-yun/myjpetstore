package myjpetstore.web.servlets;

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
public class ViewItemServlet extends HttpServlet {
    private static final String VIEW_ITEM ="/WEB-INF/jsp/catalog/Item.jsp";
    private String itemId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId = request.getParameter("itemId");
        CatalogService service = new CatalogService();
        Item item = service.getItem(itemId);
        HttpSession session = request.getSession();
        session.setAttribute("item",item);
        request.getRequestDispatcher(VIEW_ITEM).forward(request,response);
    }
}
