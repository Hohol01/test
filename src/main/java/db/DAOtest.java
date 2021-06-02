package db;

import entity.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOtest {
    private static final String SQL__GET_ALLTESTS="SELECT * FROM test";
    private static final String SQL__GET_IDBYNAME="SELECT id FROM test WHERE name=?";
    private static final String SQL__SET_TEST= "INSERT INTO TEST (name,subdgect,hardnest,time) value (?, ?, ?, ?) ";

    public void settest(String name, String subdgect, int hardnest, int time){
        Connection con = null;
        DBManager db =new DBManager();
        PreparedStatement pstm=null;
        System.out.println("insert");
        try {

            con= DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SET_TEST);
            pstm.setString(1,name);
            pstm.setString(2,subdgect);
            pstm.setInt(3,hardnest);
            pstm.setInt(4,time);
            pstm.executeUpdate();
            db.commitAndClose(con);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public int getidbyname(String name){
        int id =0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con= DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__GET_IDBYNAME);
            pstmt.setString(1, name);
            rs =pstmt.executeQuery();
            if (rs.next())
                id=rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static ArrayList<test> getlistoftests(){
        ArrayList<test> retlist= new ArrayList<test>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs= stmt.executeQuery(SQL__GET_ALLTESTS);
            testMapper mapper = new testMapper();
            test test = new test();
            while (rs.next()){

                test.setIduser(rs.getInt(Fields.test_id_sdudent));
                test.setName(rs.getString(Fields.test_name));
                test.setSubdgect(rs.getString(Fields.test_sbdgect));
                test.setHardnest(rs.getInt(Fields.test_hardnest));
                test.setTime(rs.getInt(Fields.test_time));
                test.setId(rs.getInt(Fields.test_id));
                retlist.add(test);
            }

            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }
    public static class testMapper implements EntityMapper<test>{
        @Override
        public test mapRow(ResultSet rs) {

            try {
                test test = new test();
                test.setIduser(rs.getInt(Fields.test_id_sdudent));
                test.setName(rs.getString(Fields.test_name));
                test.setSubdgect(rs.getString(Fields.test_sbdgect));
                test.setHardnest(rs.getInt(Fields.test_hardnest));
                test.setTime(rs.getInt(Fields.test_time));
                test.setId(rs.getInt(Fields.test_id));
                return test;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }
}

