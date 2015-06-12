package myjpetstore.persistence.impl;

import myjpetstore.domain.LineItem;
import myjpetstore.persistence.DBUtil;
import myjpetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2015/5/2.
 */
public class LineItemDAOImpl implements LineItemDAO {
    private final static String GETL_INEITEMS_BY_ORDERID = "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private final static String INSERT_LINEITEM = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        ArrayList<LineItem> lineitems = new ArrayList<LineItem>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GETL_INEITEMS_BY_ORDERID);
            pStatement.setInt(1, orderId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineitems.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineitems;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_LINEITEM);
            pStatement.setInt(1, lineItem.getOrderId());
            pStatement.setInt(2, lineItem.getLineNumber());
            pStatement.setString(3, lineItem.getItemId());
            pStatement.setInt(4, lineItem.getQuantity());
            pStatement.setBigDecimal(5, lineItem.getUnitPrice());
            if(pStatement.executeUpdate()!=1)
            {
                throw new Exception("insert lineItem failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
