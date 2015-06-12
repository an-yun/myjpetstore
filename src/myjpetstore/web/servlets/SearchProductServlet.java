package myjpetstore.web.servlets;

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
 * Created by zuo on 2015/5/1.
 */
public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCT = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
    private String keyword;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");

        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        HttpSession session = request.getSession();
        session.setAttribute("productList",productList);

        request.getRequestDispatcher(SEARCH_PRODUCT).forward(request,response);
    }
}
