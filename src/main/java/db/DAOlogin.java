package db;

import entity.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class DAOlogin {

    private static final String SQL_bylogin="SELECT password FROM users WHERE login=?";
    private static final String SQL__ALLPASSBYLIDIN="SELECT * FROM users";


    public String get_pass_by_login ( String login) {
        DBManager DB = new DBManager();
        PreparedStatement pstmt = null;
        Connection con=null;
        ResultSet rs=null;
        String res = null;

        try {
            con = DB.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_bylogin);
            pstmt.setString(1, login);
            rs =pstmt.executeQuery();
            if (rs.next())
                res=rs.getString(1);
            con.close();
        } catch (SQLException  ex) {
            ex.printStackTrace();
        }
        return res;
    }


   public List<user>  adminList() throws SQLException{
       List<user> adminList= new ArrayList<>();
       Statement stmt = null;
       ResultSet rs = null;
       Connection con = null;
       try {
           con = DBManager.getInstance().getConnection();

           stmt = con.createStatement();
           rs = stmt.executeQuery(SQL__ALLPASSBYLIDIN);
           while (rs.next())
               adminList.add((user) rs);
           con.close();
       } catch (SQLException  ex) {
           DBManager.getInstance().rollbackAndClose(con);
           ex.printStackTrace();
       }


       return adminList;
    }

}
