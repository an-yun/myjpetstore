package myjpetstore.persistence.impl;

import myjpetstore.domain.Account;
import myjpetstore.domain.Category;
import myjpetstore.domain.Log;
import myjpetstore.persistence.AccountDAO;
import myjpetstore.persistence.DBUtil;

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
        Account account = null;
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            pStatement.setString(1,username);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next())
            {
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getInt(15) == 1);
                account.setBannerOption(resultSet.getInt(16) == 1);
                account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account result_account = null;
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            pStatement.setString(1,account.getUsername());
            pStatement.setString(2,account.getPassword());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next())
            {
                result_account = new Account();
                result_account.setUsername(resultSet.getString(1));
                result_account.setPassword(account.getPassword());
                result_account.setEmail(resultSet.getString(2));
                result_account.setFirstName(resultSet.getString(3));
                result_account.setLastName(resultSet.getString(4));
                result_account.setStatus(resultSet.getString(5));
                result_account.setAddress1(resultSet.getString(6));
                result_account.setAddress2(resultSet.getString(7));
                result_account.setCity(resultSet.getString(8));
                result_account.setState(resultSet.getString(9));
                result_account.setZip(resultSet.getString(10));
                result_account.setCountry(resultSet.getString(11));
                result_account.setPhone(resultSet.getString(12));
                result_account.setLanguagePreference(resultSet.getString(13));
                result_account.setFavouriteCategoryId(resultSet.getString(14));
                result_account.setListOption(resultSet.getInt(15) == 1);
                result_account.setBannerOption(resultSet.getInt(16) == 1);
                result_account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
