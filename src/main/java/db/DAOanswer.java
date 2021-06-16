package db;

import entity.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOanswer {
    private final static String SQL__ADD_ANSWER = "INSERT answers (qutions_idqutions, anser, corect, number) VALUE (?, ?, ?, ?)";
    private final static String SQL__GET_ANSWER_BY_ID = "SELECT * FROM answers WHERE qutions_idqutions = ?";
    private final static String SQL__GET_CORECT_BY_ID = "SELECT  corect FROM answers WHERE id = ?";
    private static final String SQL_UPDATE="UPDATE answers SET anser = ?, corect = ?, number = ? WHERE qutions_idqutions = ?";

    public void uppdate(String text, boolean corect, int number, int idtqus){
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_UPDATE);
            pstm.setString(1, text);
            pstm.setBoolean(2,corect);
            pstm.setInt(3,number);
            pstm.setInt(4, idtqus);
            pstm.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean checkanswerbyid(int id){
        boolean check = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_CORECT_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()){
                check = rs.getBoolean(Fields.answer_corect);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    public void addanswer(int idqutions, String answer, Boolean corect, int number) {
        Connection con = null;
        PreparedStatement pstm = null;
        DBManager db = new DBManager();
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__ADD_ANSWER);
            pstm.setInt(1, idqutions);
            pstm.setString(2, answer);
            pstm.setBoolean(3, corect);
            pstm.setInt(4,number);
            pstm.executeUpdate();
            db.commitAndClose(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<answer> getanwerbyid(int id_qution) {
        ArrayList<answer> retlist = new ArrayList<answer>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_ANSWER_BY_ID);
            pstm.setInt(1, id_qution);
            rs = pstm.executeQuery();
            while (rs.next()) {
                answer answer = new answer();
                answer.setId(id_qution);
                answer.setAnswer(rs.getString(Fields.answer_answer));
                answer.setId(rs.getInt(Fields.answer_id));
                answer.setCorrect(rs.getBoolean(Fields.answer_corect));
                retlist.add(answer);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retlist;
    }
}
