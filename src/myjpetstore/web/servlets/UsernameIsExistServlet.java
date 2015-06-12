package myjpetstore.web.servlets;

import myjpetstore.domain.Account;
import myjpetstore.service.AccountService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zuo on 2015/5/13.
 */
public class UsernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username  = request.getParameter("username");
        Account account = new Account();
        account.setUsername(username);
        AccountService service = new AccountService();
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out  = response.getWriter();
        if(service.getAccount(username)!=null)
        {
            out.println("<msg>Exist</msg>");
        }
        else {
            out.println("<msg>NotExist</msg>");
        }
        out.flush();
        out.close();

    }
}
