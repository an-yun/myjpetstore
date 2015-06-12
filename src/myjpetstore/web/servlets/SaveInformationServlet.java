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
public class SaveInformationServlet extends HttpServlet {
    private static final String VIEW_ACCOUNT ="/WEB-INF/jsp/account/EditAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        String saveInformationmsg="";
        HttpSession session= request.getSession();
        Account account = (Account)session.getAttribute("account");
        if(!password.equals(""))
        {
            if(!password.equals(repeatedPassword))
            {
                saveInformationmsg = "RepeatedPassword is not the same to password!";
                session.setAttribute("saveInformationmsg", saveInformationmsg);
            }
            else
            {
                account.setPassword(password);
            }
        }
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
        account.setListOption(request.getParameterValues("account.listOption")!=null);
        account.setBannerOption(request.getParameterValues("account.bannerOption")!=null);
        AccountService accountService= new AccountService();
        accountService.updateAccount(account);
        session.setAttribute("account",account);
        request.getRequestDispatcher(VIEW_ACCOUNT).forward(request,response);
    }
}
