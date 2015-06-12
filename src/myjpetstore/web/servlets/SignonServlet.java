package myjpetstore.web.servlets;

import myjpetstore.domain.Account;
import myjpetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zuo on 2015/5/1.
 */
public class SignonServlet extends HttpServlet {
    private static final String MAIN ="/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logMessgin="";
        AccountService accountService = new AccountService();
        HttpSession session = request.getSession();
        Account account=accountService.getAccount(username,password);
        if(username==null||username.trim().equals(""))
        {
            logMessgin="the username can't be empty!";
            session.setAttribute("logMessgin",logMessgin);
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else if (accountService.getAccount(username)==null)
        {
            logMessgin="the username "+username+" is not exit";
            session.setAttribute("logMessgin",logMessgin);
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else if (account==null)
        {
            logMessgin="username or password is not correct!";
            session.setAttribute("logMessgin",logMessgin);
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        else
        {
            session.setAttribute("account",account);
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }
}
