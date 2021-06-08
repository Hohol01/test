package db;

import entity.results;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOresult {

    private static final String SQL__INSERT_IN_RESULT= "INSERT results (mark, users_id, testname) VALUE (?, ?, ?)";
    private static final String SQL__GET_ALL_BY_USERID="SELECT * FROM results WHERE users_id = ?";

    public void isertintoresults(int mark , int userid, String testname){
        Connection con = null;
        PreparedStatement prst = null;
        try{
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__INSERT_IN_RESULT);
            prst.setInt(1,mark);
            prst.setInt(2, userid);
            prst.setString(3, testname);
            prst.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<results> getresultsbyuserid(int userid){
        List<results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_ALL_BY_USERID);
            prst.setInt(1, userid);
            rs = prst.executeQuery();
            while (rs.next()){
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

        return  retlist;
    }
}