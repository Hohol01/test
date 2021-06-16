package db;

import entity.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOusers {
    private static final String SQL__idbylogin="SELECT id from users where login =?";
    private static final String SQL_bylogin="SELECT password FROM users WHERE login=?";
    private static final String SQL__ALL_PASS_BY_LIDIN ="SELECT * FROM users";
    private static final String SQL__GET_ROLE_BY_ID="SELECT role from users where id = ?";
    private static final String SQL__SET_USER="INSERT users (login, password, role , name, surname, patronymic) VALUE (?, ?, ?, ?, ?, ?)";
    private static final String SQL__DELETE_USER="DELETE FROM users WHERE id = ?";
    private static final String SQL__UPDATE_USER="UPDATE users set login = ?, password = ?, role = ?, name =?, surname = ?, patronymic = ?, block = ? WHERE id = ?";
    private static final String SQL__GET_USER_BY_ID="SELECT * FROM users WHERE id = ?";

    public boolean getblock(int id){
        boolean block = false;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_USER_BY_ID);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next())
                block = rs.getBoolean(Fields.users_block);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }

    public ArrayList<user> getuserbyid(int id){
        ArrayList<user> retlist = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        userMapper userMapper = new userMapper();
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_USER_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                retlist.add(userMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retlist;
    }

    public void edituser(String surname, String name, String patronymic,
                         String role, String login, String password, boolean block, int id){
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager db = new DBManager();
        try {
            con=DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__UPDATE_USER);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setString(4, name);
            pstmt.setString(5, surname);
            pstmt.setString(6, patronymic);
            pstmt.setBoolean(7,block);
            pstmt.setInt(8, id);
            pstmt.executeUpdate();
            db.commitAndClose(con);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteuser(int id){
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__DELETE_USER);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void adduser(String surname, String name, String patronymic,
                        String role, String login, String password){
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager db = new DBManager();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__SET_USER);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setString(4, name);
            pstmt.setString(5, surname);
            pstmt.setString(6, patronymic);
            pstmt.executeUpdate();
            db.commitAndClose(con);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getrolebyid(int id){
        String role = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            Connection con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__GET_ROLE_BY_ID);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if (rs.next())
                role = rs.getString(Fields.users_role);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }

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


    public ArrayList<user> usersList(){
        ArrayList<user> List = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        userMapper userMapper = new userMapper();
        try {
            con = DBManager.getInstance().getConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__ALL_PASS_BY_LIDIN);
            while (rs.next()){
                List.add(userMapper.mapRow(rs));
            }

            con.close();
        } catch (SQLException  ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }


        return List;
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
    private static class userMapper implements EntityMapper<user>{

        @Override
        public  user mapRow(ResultSet rs) {
            user user = new user();
            try {
                user.setId(rs.getInt(Fields.users_id));
                user.setName(rs.getString(Fields.user_name));
                user.setPatronymic(rs.getString(Fields.user_patronymic));
                user.setSurname(rs.getString(Fields.user_surname));
                user.setLogin(rs.getString(Fields.users_login));
                user.setBlock(rs.getBoolean(Fields.users_block));
                user.setRole(rs.getString(Fields.users_role));
                user.setPassword(rs.getString(Fields.users_password));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return user;
        }
    }
}
