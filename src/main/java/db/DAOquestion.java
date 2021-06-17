package db;

import entity.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOquestion {
    private static final String SQL__SET_QUTION = "INSERT INTO question (text , idtest, number) values( ?, ?, ?)";
    private static final String SQL__GET_ID_BY_TEXT ="SELECT id, idtest  FROM question WHERE text=?";
    private static final String SQL__GET_ALL_BY_TESTID="SELECT * FROM question WHERE idtest = ?";
    private static final String SQL__SELECT_ALL_BY_IDTEST_AND_NUMBER="SELECT * FROM question WHERE (idtest =? and number=?)";
    private static final String SQL__GET_IDTEST_BY_ID="SELECT idtest FROM question where id = ?";
    private static final String SQL__GET_ID_BY_TEXT_AND_TESTID="SELECT ID FROM question WHERE ( text = ? AND idtest = ?)";
    private static final String SQL_UPDATE="UPDATE  question SET text = ? WHERE id = ?";
    private static final String SQL__SET_QUTION_BY_NUMBER="SELECT * FROM question WHERE number = ?";
    private static final String SQL__DELETE_DY_ID = "DELETE FROM question WHERE idtest = ?";

    public void delete(int testid){
        PreparedStatement pstm = null;
        Connection con=null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__DELETE_DY_ID);
            pstm.setInt(1, testid);
            pstm.executeUpdate();
            DBManager.commitAndClose(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean checknext(int number){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean returnbool = false;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SET_QUTION_BY_NUMBER);
            pstm.setInt(1,number);
            rs = pstm.executeQuery();
            if (rs.next())
                returnbool = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnbool;
    }

    public void updatequs(String text, int id){
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_UPDATE);
            pstm.setString(1, text);
            pstm.setInt(2, id);
            pstm.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getidbynumberandtestid(int number, int testid){
        int id=0;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SELECT_ALL_BY_IDTEST_AND_NUMBER);
            pstm.setInt(1, testid);
            pstm.setInt(2, number);
            rs = pstm.executeQuery();
            if (rs.next())
                id = rs.getInt(Fields.question_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return id;
    }
    
    public int getquantityoftest(int testid){
        int quantity=0;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_ALL_BY_TESTID);
            pstm.setInt(1,testid);
            rs = pstm.executeQuery();
            while (rs.next())
                quantity++;
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return quantity;
    }

    public int getidtestbyid(int id){
        int idtest=0;
        Connection con =null;
        PreparedStatement pstm = null;
        ResultSet rs= null;
        try {
            con=DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_IDTEST_BY_ID);
            pstm.setString(1, String.valueOf(id));
            rs = pstm.executeQuery();
           if (rs.next())
               idtest = rs.getInt(Fields.question_idtest);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idtest;
    }

    public void setQution(String text,long idtest, int number){
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager db = new DBManager();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__SET_QUTION);
            pstmt.setString(1, text);
            pstmt.setLong(2,idtest);
            pstmt.setInt(3,number);
            pstmt.executeUpdate();
            db.commitAndClose(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int get_idbyname(String text, int idtest){
        Connection con =null;
        PreparedStatement pstm = null;
        ResultSet rs= null;
        int id= 0;
        try {
            con= DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_ID_BY_TEXT);
            pstm.setString(1,text);
            rs = pstm.executeQuery();
            while (rs.next())
                if (rs.getInt(2)==idtest) {
                    id = rs.getInt(1);
                break;
                }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }


    public List<question> getqustions(int idtest, int number){
        List<question> retlist= new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm=null;
        ResultSet rs = null;
        try {
            con= DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SELECT_ALL_BY_IDTEST_AND_NUMBER);
            pstm.setInt(1, idtest);
            pstm.setInt(2, number);
            rs = pstm.executeQuery();
            while (rs.next()){
                question question = new question();
                question.setId(rs.getInt(Fields.question_id));
                question.setNumber(rs.getInt(Fields.question_number));
                question.setText(rs.getString(Fields.question_text));
                question.setIdtest(rs.getInt(Fields.question_idtest));

                retlist.add(question);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }
}
