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
 * Created by zuo on 2015/5/2.
 */
public class NewAccountServlet extends HttpServlet {
    private static final String NEW_ACCOUNT ="/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        String newAccountError="";
        HttpSession session= request.getSession();
        AccountService accountService = new AccountService();
        Account account = new Account();
        if (username.equals(""))
        {
            newAccountError="username can't be empty!";
            session.setAttribute("newAccountError", newAccountError);
            request.getRequestDispatcher(NEW_ACCOUNT).forward(request, response);
        }
        else if(accountService.getAccount(username)!=null)
        {
            newAccountError="the username "+username +" has been existed!";
            session.setAttribute("newAccountError", newAccountError);
            request.getRequestDispatcher(NEW_ACCOUNT).forward(request, response);
        }
        else if(password.equals(""))
        {
            newAccountError = "password can't be empty!";
            session.setAttribute("newAccountError", newAccountError);
            request.getRequestDispatcher(NEW_ACCOUNT).forward(request, response);
        }
        else if(!password.equals(repeatedPassword))
        {
            newAccountError = "RepeatedPassword is not the same to password!";
            session.setAttribute("newAccountError", newAccountError);
            request.getRequestDispatcher(NEW_ACCOUNT).forward(request, response);
        }
        else
        {
            account.setUsername(username);
            account.setPassword(password);
            account.setFirstName(request.getParameter("account.firstName"));
            account.setLastName(request.getParameter("account.lastName"));
            account.setEmail(request.getParameter("account.email"));
            account.setPhone(request.getParameter("account.phone"));
            account.setAddress1(request.getParameter("account.address1"));
            account.setAddress2(request.getParameter("account.address2"));
            account.setCity(request.getParameter("account.city"));
            account.setState(request.getParameter("account.state"));
            account.setZip(request.getParameter("account.zip"));
            account.setCountry(request.getParameter("account.country"));
            account.setLanguagePreference(request.getParameter("account.languagePreference"));
            account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
            account.setListOption(request.getParameterValues("account.listOption") != null);
            account.setBannerOption(request.getParameterValues("account.bannerOption") != null);
            accountService.insertAccount(account);
            session.setAttribute("account", account);
            session.setAttribute("message", "\n\n                     Register success!\n\n");
            session.setAttribute("newAccountError", null);
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}
