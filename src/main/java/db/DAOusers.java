package db;

import entity.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOusers {
    private static final String SQL__idbylogin="SELECT id from users where login =?";
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


    public List<user> adminList() throws SQLException{
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





    public int getidbylogin(String login){
        Connection con=null;
        PreparedStatement pstm =null;
        ResultSet rs=null;
        int id=0;
        try {
            con=DBManager.getInstance().getConnection();
            pstm= con.prepareStatement(SQL__idbylogin);
            pstm.setString(1,login);
            rs = pstm.executeQuery();
            if (rs.next())
                id=rs.getInt(Fields.users_id);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;

    }
}
