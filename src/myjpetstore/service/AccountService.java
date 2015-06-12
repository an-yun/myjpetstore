package myjpetstore.service;

import myjpetstore.domain.Account;
import myjpetstore.domain.Log;
import myjpetstore.persistence.AccountDAO;
import myjpetstore.persistence.impl.AccountDAOImpl;

import java.util.List;

/**
 * Created by zuo on 2015/5/1.
 */
public class AccountService {
    private AccountDAO accountDAO = new AccountDAOImpl();
    public Account getAccount(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDAO.getAccountByUsernameAndPassword(account);
    }


    public void insertAccount(Account account) {
        accountDAO.insertAccount(account);
        accountDAO.insertProfile(account);
        accountDAO.insertSignon(account);
    }


    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
        accountDAO.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDAO.updateSignon(account);
        }
    }
    public void addLog(Log log)
    {
        accountDAO.addLog(log);
    }
    public List<Log> getLogList(){return accountDAO.getLogList();}
}
