package db;

import entity.test;

import java.sql.*;
import java.util.ArrayList;


public class DAOtest {
    private static final String SQL__GET_ALL_TESTS = "SELECT * FROM test";
    private static final String SQL__GET_ID_BY_NAME = "SELECT id FROM test WHERE name=?";
    private static final String SQL__SET_TEST = "INSERT test (name,subdgect,hardnest,time) value (?, ?, ?, ?) ";
    private static final String SQL__GET_TEST_BY_ID = " SELECT * FROM test WHERE id=?";
    private static final String SQL__GET_NAME_BY_ID = "SELECT name FROM test WHERE id = ?";
    private static final String SQL__SERCH_BY_NAME = "SELECT * FROM test WHERE name LIKE ?";
    private static final String SQL__GET_ALL_BY_SUBDGECT = "SELECT * FROM test WHERE subdgect = ?";
    private static final String SQL__SORT_BY_VALUE = "SELECT * FROM  test ORDER BY";
    private static final String SQL__GET_TIME_BY_ID = "SELECT time FROM test WHERE id = ?";
    private static final String SQL__UPDATE_BY_ID = "UPDATE test SET name = ?, subdgect = ?, hardnest = ?, time = ? WHERE id =?";
    private static final String SQL__DELETE_DY_ID = "DELETE FROM test WHERE id = ?";

    public void delete(int id) {
        PreparedStatement pstm = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__DELETE_DY_ID);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            DBManager.commitAndClose(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(String name, String subdgect, int hardnest, int time, int id) {
        PreparedStatement pstm = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__UPDATE_BY_ID);
            pstm.setString(1, name);
            pstm.setString(2, subdgect);
            pstm.setInt(3, hardnest);
            pstm.setInt(4, time);
            pstm.setInt(5, id);
            pstm.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int gettimebyid(int id) {
        int time = 0;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_TIME_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                time = rs.getInt(Fields.test_time);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return time;
    }

    public ArrayList<test> sortbyvalue(String search) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<test> retlist = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SORT_BY_VALUE + " " + search);
            rs = pstm.executeQuery();
            testMapper mapper = new testMapper();
            while (rs.next())
                retlist.add(mapper.mapRow(rs));
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }

    public ArrayList<test> getlistoftestswithsubdgect(String name) {
        ArrayList<test> retlist = new ArrayList<test>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_ALL_BY_SUBDGECT);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            test test = new test();
            testMapper mapper = new testMapper();
            while (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }


    public ArrayList<test> getlistbyname(String name) {
        ArrayList<test> retlist = new ArrayList<test>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SERCH_BY_NAME);
            pstm.setString(1, "%" + name + "%");
            rs = pstm.executeQuery();
            testMapper mapper = new testMapper();
            test test = new test();
            while (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }

            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }

    public String gettextbyid(int id) {
        String text = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_NAME_BY_ID);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();
            if (rs.next())
                text = rs.getString(Fields.test_name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return text;
    }

    public ArrayList<test> gettesttsbyid(int id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<test> retlist = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__GET_TEST_BY_ID);
            pstm.setInt(1, id);
            testMapper mapper = new testMapper();
            rs = pstm.executeQuery();
            if (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retlist;
    }

    public void settest(String name, String subdgect, int hardnest, int time) {
        Connection con = null;
        DBManager db = new DBManager();
        PreparedStatement pstm = null;
        System.out.println("insert");
        try {

            con = DBManager.getInstance().getConnection();
            pstm = con.prepareStatement(SQL__SET_TEST);
            pstm.setString(1, name);
            pstm.setString(2, subdgect);
            pstm.setInt(3, hardnest);
            pstm.setInt(4, time);
            pstm.executeUpdate();
            db.commitAndClose(con);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public int getidbyname(String name) {
        int id = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__GET_ID_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next())
                id = rs.getInt(1);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public ArrayList<test> getlistoftests() {
        ArrayList<test> retlist = new ArrayList<test>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__GET_ALL_TESTS);
            testMapper mapper = new testMapper();
            test test = new test();
            while (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }

            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retlist;
    }

    private static class testMapper implements EntityMapper<test> {
        @Override
        public test mapRow(ResultSet rs) {

            try {
                test test = new test();
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

