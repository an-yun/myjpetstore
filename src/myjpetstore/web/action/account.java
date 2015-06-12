package myjpetstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myjpetstore.domain.Account;
import myjpetstore.domain.Log;
import myjpetstore.domain.Order;
import myjpetstore.service.AccountService;
import myjpetstore.service.OrderService;
import myjpetstore.web.validation.ValidationCodeImage;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by zuo on 2015/5/30.
 */
public class account extends ActionSupport {
    private ActionContext actionContext = ActionContext.getContext();
    private Map session = actionContext.getSession();
    private AccountService accountService = new AccountService();
    private HttpServletRequest request = ServletActionContext.getRequest();

    private String username;
    private String password;
    private String repeatedPassword;
    private String messgin;


    private Account account;
    private String logMsg;

    private String saveInformationmsg;

    private String validationCode;

    private List<Log> logList;



    public String usernameIsExist() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (accountService.getAccount(username) != null) {
            out.println("<msg>Exist</msg>");
        } else {
            out.println("<msg>NotExist</msg>");
        }
        out.flush();
        out.close();
        return SUCCESS;
    }

    public String signonForm() throws Exception{
        request.setCharacterEncoding("UTF-8");
        return SUCCESS;
    }
    public String signon()
    {
        session.put("account", account);
        return SUCCESS;
    }
    public String newAccountForm()
    {
        return SUCCESS;
    }
    public String newAccout()
    {
        if (username.equals("")||accountService.getAccount(username)!=null) return INPUT;
        else if(password.equals("")||!password.equals(repeatedPassword)) return ERROR;
        else
        {
            account.setUsername(username);
            account.setPassword(password);
            accountService.insertAccount(account);
            session.put("account", account);
            messgin ="\n\n                     Register success!\n\n";
            return SUCCESS;
        }
    }
    public String myCount()
    {
        return SUCCESS;
    }


    public String signOff()
    {
        session.put("account",null);
        session.put("cart",null);
        session.put("order",null);

        return SUCCESS;
    }
    public String saveInformation()
    {
        if((!password.equals(""))&&(!password.equals(repeatedPassword)))
        {
                saveInformationmsg = "RepeatedPassword is not the same to password!";
                return INPUT;
        }
        account.setPassword(password);
        account.setUsername(((Account) session.get("account")).getUsername());
        accountService.updateAccount(account);
        session.put("account",account);
        return SUCCESS;
    }
    public String listOrders()
    {
        Account account = (Account)session.get("account");
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
        session.put("orderList",orderList);
        return SUCCESS;
    }
    public String validationCode() throws Exception
    {
        ValidationCodeImage validationCodeImage =new ValidationCodeImage();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        response.setContentLength(validationCodeImage.getImageBytes().length);

        ServletOutputStream sos = response.getOutputStream();
        sos.write(validationCodeImage.getImageBytes());
        sos.close();
        session.put("validationCode", validationCodeImage.getValidationCode());
        return SUCCESS;
    }
    public void validateSignon()
    {
        String username = account.getUsername(),password = account.getPassword();
        if(username==null||username.trim().equals("")||accountService.getAccount(username)==null)
        {
            logMsg=getText("logMsgUsername");
            addActionError("Error");
            return;
        }
        else if((account =accountService.getAccount(username,password))==null)
        {
            logMsg=getText("logMsgPassword");
            addActionError("Error");
            return;
        }

        else if (!validationCode.equals(session.get("validationCode")))
        {
            logMsg=getText("validationError");
            addActionError("Error");
        }
    }
    public String viewLog()
    {
        logList = accountService.getLogList();
        return SUCCESS;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getMessgin() {
        return messgin;
    }

    public void setMessgin(String messgin) {
        this.messgin = messgin;
    }
    public String getSaveInformationmsg() {
        return saveInformationmsg;
    }

    public void setSaveInformationmsg(String saveInformationmsg) {
        this.saveInformationmsg = saveInformationmsg;
    }
    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }
}
