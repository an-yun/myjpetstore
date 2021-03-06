package myjpetstore.persistence.impl;

import myjpetstore.domain.Account;
import myjpetstore.domain.Log;
import myjpetstore.persistence.AccountDAO;
import myjpetstore.persistence.DBUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2015/5/1.
 */
public class AccountDAOImpl implements AccountDAO{
    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP,ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP,ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ?  AND SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String INSERT_ACCOUNT = " INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_PROFILE = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY,MYLISTOPT,BANNEROPT,USERID) VALUES (?, ?, ? , ?, ?)";
    private static final String INSERT_SIGNON ="INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";
    private static final String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, STATUS = ?, ADDR1 = ?,  ADDR2 = ?, CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, PHONE = ? WHERE USERID = ?";
    private static final String UPDATE_PROFILE ="UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?,MYLISTOPT=?,BANNEROPT=? WHERE USERID = ?";
    private static final String UPDATE_SIGNON =" UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
    private static final String ADDLOG ="INSERT INTO LOG (TIME,EVENT) VALUES(?,?)";
    private static final String GET_LOGLIST = "SELECT TIME, EVENT FROM LOG";
    @Override
    public Account getAccountByUsername(String username) {
        String hsql = "from Account where username=?";
        Account account;
        Session sessionHibernate =  HibernateUtil.getSession();
        sessionHibernate.beginTransaction();
        Query query = sessionHibernate.createQuery(hsql);
        query.setString(0, username);
        ArrayList<Account> result = (ArrayList<Account>)query.list();
        if(result.size()==0)    return null;
        else {
            account = (Account) sessionHibernate.load(Account.class, username);
            sessionHibernate.getTransaction().commit();
            return account;
        }
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account result_account = null;
//        String hsql = "from Account,where username=? and password=?";
//        Session sessionHibernate =  HibernateUtil.getSession();
//        sessionHibernate.beginTransaction();//开始事物；
//        Query query = sessionHibernate.createQuery(hsql);
//        query.setString(0, username);
//        query.setString(1, password);
//        ArrayList<User> result = (ArrayList<User>)query.list();
//        sessionHibernate.getTransaction().commit();//结束事务
//        if(result.size()>0){
//            return "ok";
//        }else{
//            return "failed";
//        }
        return result_account;
    }

    @Override
    public void insertAccount(Account account) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_ACCOUNT);
            pStatement.setString(1, account.getEmail());
            pStatement.setString(2, account.getFirstName());
            pStatement.setString(3, account.getLastName());
            pStatement.setString(4, account.getStatus());
            pStatement.setString(5, account.getAddress1());
            pStatement.setString(6, account.getAddress2());
            pStatement.setString(7, account.getCity());
            pStatement.setString(8, account.getState());
            pStatement.setString(9, account.getZip());
            pStatement.setString(10, account.getCountry());
            pStatement.setString(11, account.getPhone());
            pStatement.setString(12, account.getUsername());

            if(pStatement.executeUpdate()!=1)
            {
               throw new Exception("insert account failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_PROFILE);
            pStatement.setString(1, account.getLanguagePreference());
            pStatement.setString(2, account.getFavouriteCategoryId());
            pStatement.setInt(3, account.isListOption()?1:0);
            pStatement.setInt(4, account.isBannerOption()?1:0);
            pStatement.setString(5, account.getUsername());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("insert profile failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_SIGNON);
            pStatement.setString(1, account.getPassword());
            pStatement.setString(2, account.getUsername());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("insert signon failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            pStatement.setString(1, account.getEmail());
            pStatement.setString(2, account.getFirstName());
            pStatement.setString(3, account.getLastName());
            pStatement.setString(4, account.getStatus());
            pStatement.setString(5, account.getAddress1());
            pStatement.setString(6, account.getAddress2());
            pStatement.setString(7, account.getCity());
            pStatement.setString(8, account.getState());
            pStatement.setString(9, account.getZip());
            pStatement.setString(10, account.getCountry());
            pStatement.setString(11, account.getPhone());
            pStatement.setString(12, account.getUsername());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("update account failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_PROFILE);
            pStatement.setString(1, account.getLanguagePreference());
            pStatement.setString(2, account.getFavouriteCategoryId());
            pStatement.setInt(3,account.isListOption()?1:0);
            pStatement.setInt(4,account.isBannerOption()?1:0);
            pStatement.setString(5, account.getUsername());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("update profile failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_SIGNON);
            pStatement.setString(1, account.getPassword());
            pStatement.setString(2, account.getUsername());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("update signon failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addLog(Log log) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(ADDLOG);
            pStatement.setString(1, log.getTime());
            pStatement.setString(2, log.getEvent());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("add log failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Log> getLogList() {
        List<Log> logList = new ArrayList<Log>();
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_LOGLIST);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next())
            {
                Log log = new Log();
                log.setTime(resultSet.getString(1));
                log.setEvent(resultSet.getString(2));
                logList.add(log);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return logList;
    }
}
