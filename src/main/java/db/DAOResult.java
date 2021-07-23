package db;

import entity.Results;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOResult {

    private static final Logger log = Logger.getLogger(DAOResult.class);

    private static final String SQL__INSERT_IN_RESULT = "INSERT results (mark, users_id, testname, test_id) VALUE (?, ?, ?, ?)";
    private static final String SQL__GET_ALL_BY_USERID = "SELECT * FROM results WHERE users_id = ? LIMIT ?,3";
    private static final String SQL__GET_RESULTS_FOR_TEACHER = "SELECT * FROM results LEFT JOIN users ON results.users_id = users.id WHERE test_id = ? LIMIT ?,3";
    private static final String SQL__GET_COUNT_OF_RESULTS ="SELECT COUNT(1) AS count FROM results WHERE users_id = ?";
    private static final String SQL__GET_COUNT_OF_RESULTS_BY_TEST_ID ="SELECT COUNT(1) AS count FROM results WHERE test_id = ?";

    public int getCountOfResultsTestId(int userId){
        int ret = 0;
        List<Results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_COUNT_OF_RESULTS_BY_TEST_ID);
            prst.setInt(1, userId);
            rs = prst.executeQuery();
            if (rs.next())
                ret = rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    public int getCountOfResults(int userId){
        int ret = 0;
        List<Results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_COUNT_OF_RESULTS);
            prst.setInt(1, userId);
            rs = prst.executeQuery();
            if (rs.next())
                ret = rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }



    public List<Results> getResultsForTeacher(int idtest, int page) {
        List<Results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_RESULTS_FOR_TEACHER);
            prst.setInt(1, idtest);
            prst.setInt(2,page);
            rs = prst.executeQuery();
            while (rs.next()) {
                Results results = new Results();
                results.setId(idtest);
                results.setMark(rs.getInt(Fields.results_mark));
                results.setNameuser(rs.getString(Fields.user_name));
                results.setPatronymic(rs.getString(Fields.user_patronymic));
                results.setSurname(rs.getString(Fields.user_surname));
                retlist.add(results);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }

    public void isertIntoResults(int mark, int userid, String testname, int testid) {
        Connection con = null;
        PreparedStatement prst = null;
        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__INSERT_IN_RESULT);
            prst.setInt(1, mark);
            prst.setInt(2, userid);
            prst.setString(3, testname);
            prst.setInt(4, testid);
            prst.executeUpdate();
            DBManager.commitAndClose(con);
            log.debug("inser copmpleate");
        } catch (SQLException throwables) {
            DBManager.rollbackAndClose(con);
            log.debug("error");
            throwables.printStackTrace();
        }
    }

    public List<Results> getResultsByUserid(int userid, int count) {
        List<Results> ret = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_ALL_BY_USERID);
            prst.setInt(1, userid);
            prst.setInt(2, count);
            rs = prst.executeQuery();
            while (rs.next()) {
                Results results = new Results();
                results.setId(userid);
                results.setUserid(rs.getInt(Fields.results_userid));
                results.setName(rs.getString(Fields.results_testname));
                results.setMark(rs.getInt(Fields.results_mark));
                ret.add(results);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }
}
