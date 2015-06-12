package myjpetstore.persistence.impl;

import myjpetstore.domain.Category;
import myjpetstore.persistence.CategoryDAO;
import myjpetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2015/4/25.
 */
public class CategoryDAOImpl implements CategoryDAO
{
    private static final String GET_CATEGORY_LIST = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY WHERE CATID = ?";

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoriesList = new ArrayList<Category>();
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_CATEGORY_LIST);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next())
            {
                Category category = new Category();
                category.setCategoryId((resultSet.getString(1)));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
                categoriesList.add(category);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return categoriesList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try{
            Connection connection = new DBUtil().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_CATEGORY);
            pStatement.setString(1,categoryId);
            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next())
            {
                category = new Category();
                category.setCategoryId((resultSet.getString(1)));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return category;
    }
//    public static void main(String[] args) throws Exception {
//        CategoryDAOImpl c= new CategoryDAOImpl();
//        List<Category> list = c.getCategoryList();
//        Category temp = c.getCategory("BIRDS");
//        System.out.println("��ѯ�����"+temp.getName());
//        for (Category category:list)
//            System.out.println(category.getName());
//    }
}
