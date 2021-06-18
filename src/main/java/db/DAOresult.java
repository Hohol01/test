package db;

import entity.results;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOresult {

    private static final String SQL__INSERT_IN_RESULT = "INSERT results (mark, users_id, testname, test_id) VALUE (?, ?, ?, ?)";
    private static final String SQL__GET_ALL_BY_USERID = "SELECT * FROM results WHERE users_id = ?";
    private static final String SQL__GET_RESULTS_FOR_TEACHER = "SELECT * FROM results LEFT JOIN users ON results.users_id = users.id WHERE test_id = ?";

    public List<results> getresultsfortracher(int idtest){
        List<results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_RESULTS_FOR_TEACHER);
            prst.setInt(1, idtest);
            rs = prst.executeQuery();
            while (rs.next()) {
                results results = new results();
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

    public void isertintoresults(int mark, int userid, String testname, int testid) {
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
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<results> getresultsbyuserid(int userid) {
        List<results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_ALL_BY_USERID);
            prst.setInt(1, userid);
            rs = prst.executeQuery();
            while (rs.next()) {
                results results = new results();
                results.setId(userid);
                results.setUserid(rs.getInt(Fields.results_userid));
                results.setName(rs.getString(Fields.results_testname));
                results.setMark(rs.getInt(Fields.results_mark));
                retlist.add(results);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }
}
