package myjpetstore.web.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import myjpetstore.domain.Account;
import myjpetstore.domain.Log;
import myjpetstore.service.AccountService;

import java.util.Date;
import java.util.Map;

/**
 * Created by zuo on 2015/6/6.
 */
public class LogInterceptor implements Interceptor
{

    public void destroy() {
    }
    public void init() {
    }
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        AccountService accountService = new AccountService();
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        String time = (new Date()).toString() ,username;
        Account account = (Account)session.get("account");
        if(account == null)
            username = "未登陆用户";
        else username = account.getUsername();
        username+="请求执行"+actionInvocation.getInvocationContext().getName()+"方法";
        accountService.addLog(new Log(time,username));
        return actionInvocation.invoke();
    }
}
