package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOusers {
    private static final String SQL__idbylogin="SELECT id from users where login =?";

    public int getidbylogin(String login){
        Connection con=null;
        PreparedStatement pstm =null;
        ResultSet rs=null;
        int id=0;
        try {
            con=DBManager.getInstance().getConnection();
            pstm= con.prepareStatement(SQL__idbylogin);
            pstm.setString(1,login);
            rs= pstm.executeQuery();

            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;

    }
}
