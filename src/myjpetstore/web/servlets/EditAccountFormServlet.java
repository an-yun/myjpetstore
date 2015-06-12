package myjpetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zuo on 2015/5/1.
 */
public class EditAccountFormServlet extends HttpServlet {
    private static final String VIEW_ACCOUNT ="/WEB-INF/jsp/account/EditAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_ACCOUNT).forward(request,response);
    }
}
