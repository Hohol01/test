package db;

import entity.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOanswer {
    private final static String SQL__ADD_ANSWER="INSERT answers (qutions_idqutions, anser, corect) VALUE (?, ?, ?)";
    private final static String SQL__GET_ANSWER_BY_ID="SELECT * FROM answers WHERE qutions_idqutions = ?";

        public void addanswer(int idqutions, String answer, Boolean corect){
            Connection con=null;
            PreparedStatement pstm = null;
            DBManager db = new DBManager();
            try {
                con=DBManager.getInstance().getConnection();
                pstm = con.prepareStatement(SQL__ADD_ANSWER);
                pstm.setInt(1,idqutions);
                pstm.setString(2,answer);
                pstm.setBoolean(3,corect);
                pstm.executeUpdate();
                db.commitAndClose(con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        public ArrayList<answer> getanwerbyid(int id_qution){
            ArrayList<answer> retlist = new ArrayList<answer>();
            Connection con=null;
            PreparedStatement pstm=null;
            ResultSet rs=null;

            try {
                con= DBManager.getInstance().getConnection();
                pstm = con.prepareStatement(SQL__GET_ANSWER_BY_ID);
                pstm.setInt(1,id_qution);
                rs=pstm.executeQuery();
                while (rs.next()){
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
