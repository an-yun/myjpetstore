package myjpetstore.persistence.impl;

import myjpetstore.domain.Product;
import myjpetstore.persistence.DBUtil;
import myjpetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2015/4/25.
 */
public class ProductDAOImpl implements ProductDAO
{
    private static final String getProductListByCategoryString = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY=?";
    private static final String getProductString = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String searchProductListString = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    private static final String getSuggestByKeyword =" SELECT name FROM product WHERE name like ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(getProductListByCategoryString);
            pStatement.setString(1, categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                products.add(product);
            }
            //System.out.println(products.size());
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(getProductString);
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(searchProductListString);
            pStatement.setString(1, keywords);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public String getSuggestByKeyword(String keyword) {
        String result = new String();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(getSuggestByKeyword);
            pStatement.setString(1,"%"+keyword+"%");
            ResultSet resultSet=pStatement.executeQuery();

            while (resultSet.next()){
                if(!resultSet.isLast())
                    result += resultSet.getString(1)+"|";
                else
                    result += resultSet.getString(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
    }
}
