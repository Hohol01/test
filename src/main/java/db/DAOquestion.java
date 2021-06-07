package db;

import entity.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOquestion {
    private static final String SQL__SET_QUTION = "INSERT INTO qutions (text , idtest, number) values( ?, ?, ?)";
    private static final String SQL__GET_ID_BY_TEXT ="SELECT id, idtest  FROM qutions WHERE text=?";
    private static final String SQL__SELECT_ALL_BY_ID="SELECT * FROM qutions WHERE id =?";
    private static final String SQL__GET_IDTEST_BY_ID="SELECT idtest FROM qutions where id = ?";

    


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


    public ArrayList<question> getqustions(int id){
        ArrayList<question> retlist= new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm=null;
        ResultSet rs = null;
        try {
            con= DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SELECT_ALL_BY_ID);
            pstm.setString(1, String.valueOf(id));
            rs = pstm.executeQuery();
            while (rs.next()){
                question question = new question();
                question.setId(id);
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
