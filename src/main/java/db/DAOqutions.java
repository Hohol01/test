package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOqutions {
    static final String SQL__SETQUTION= "INSERT INTO qutions (text , idtest) values( ?, ?)";
    static final String SQL__GETIDBYTEXT ="SELECT id  FROM qutions where text=?";

    public void setQution(String text,long idtest){
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager db = new DBManager();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__SETQUTION);
            pstmt.setString(1, text);
            pstmt.setLong(2,idtest);
            pstmt.executeUpdate();
            db.commitAndClose(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public int get_idbyname(String text){
        Connection con =null;
        PreparedStatement pstm = null;
        ResultSet rs= null;
        int id= Integer.parseInt(null);
        try {
            con= DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GETIDBYTEXT);
            pstm.setString(1,text);
            rs = pstm.executeQuery();
            while (rs.next())
                id=rs.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
}
