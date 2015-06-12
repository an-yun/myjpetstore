package myjpetstore.persistence.impl;

import myjpetstore.domain.Sequence;
import myjpetstore.persistence.DBUtil;
import myjpetstore.persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by zuo on 2015/5/2.
 */
public class SequenceDAOImpl implements SequenceDAO {
    private final static String GET_SEQUENCE = "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private final static String UPDATE_SEQUENCE ="UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";
    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence result_sequence = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_SEQUENCE);
            pStatement.setString(1, sequence.getName());
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result_sequence = new Sequence();
                result_sequence.setName(resultSet.getString(1));
                result_sequence.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_sequence;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            pStatement.setInt(1, sequence.getNextId());
            pStatement.setString(2, sequence.getName());
            if (pStatement.executeUpdate() != 1) {
                throw new Exception("update sequence failed!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
