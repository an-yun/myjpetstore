package myjpetstore.persistence;

import myjpetstore.domain.Account;
import myjpetstore.domain.Category;
import myjpetstore.domain.Log;

import java.util.List;

/**
 * Created by zuo on 2015/5/1.
 */
public interface AccountDAO {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);

    void addLog(Log log);
    List<Log> getLogList();
}
