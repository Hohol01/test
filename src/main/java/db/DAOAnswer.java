package db;

import entity.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAnswer {
    private final static String SQL__ADD_ANSWER = "INSERT answers (qutions_idqutions, anser, corect, number, test_id) VALUE (?, ?, ?, ?, ?)";
    private final static String SQL__GET_ANSWER_BY_ID = "SELECT * FROM answers WHERE qutions_idqutions = ?";
    private final static String SQL__GET_CORECT_BY_ID = "SELECT  corect FROM answers WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE answers SET anser = ?, corect = ? WHERE qutions_idqutions = ? AND number = ?";
    private static final String SQL__DELETE_DY_ID = "DELETE FROM answers WHERE  test_id = ?";

    public void delete(int testid) {
        PreparedStatement pstm = null;
        Connection con = null;
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

    public void update(String text, boolean corect, int number, int idtqus) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_UPDATE);
            pstm.setString(1, text);
            pstm.setBoolean(2, corect);
            pstm.setInt(3, idtqus);
            pstm.setInt(4, number);
            pstm.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean checkAnswerById(int id) {
        boolean check = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_CORECT_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                check = rs.getBoolean(Fields.answer_corect);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    public void addAnswer(int idqutions, String answer, Boolean corect, int number, int testid) {
        Connection con = null;
        PreparedStatement pstm = null;
        DBManager db = new DBManager();
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__ADD_ANSWER);
            pstm.setInt(1, idqutions);
            pstm.setString(2, answer);
            pstm.setBoolean(3, corect);
            pstm.setInt(4, number);
            pstm.setInt(5, testid);
            pstm.executeUpdate();
            db.commitAndClose(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Answer> getAnwerById(int id_qution) {
        ArrayList<Answer> retlist = new ArrayList<Answer>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_ANSWER_BY_ID);
            pstm.setInt(1, id_qution);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
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
