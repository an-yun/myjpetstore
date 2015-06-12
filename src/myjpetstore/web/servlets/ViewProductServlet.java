package myjpetstore.web.servlets;

import myjpetstore.domain.Item;
import myjpetstore.domain.Product;
import myjpetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zuo on 2015/4/25.
 */
public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT ="/WEB-INF/jsp/catalog/Product.jsp";
    private String productId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product = service.getProduct(productId);
        List<Item> itemList = service.getItemListByProduct(productId);

        HttpSession session = request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);

        request.getRequestDispatcher(VIEW_PRODUCT).forward(request,response);
    }
}
