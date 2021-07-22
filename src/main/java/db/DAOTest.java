package db;

import entity.Results;
import entity.Subject;
import entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAOTest {
    private static final String SQL__LIMIT = " LIMIT ?,3";
    private static final String SQL_SELECT_LANG_EN = " INNER JOIN en ON idsubdgect = idsub";
    private static final String SQL_SELECT_LANG_RU = " INNER JOIN ru ON idsubdgect = idsub";
    private static final String SQL__GET_ALL_TESTS = "SELECT * FROM test";
    private static final String SQL__GET_ID_BY_NAME = "SELECT id FROM test WHERE name=?";
    private static final String SQL__SET_TEST = "INSERT test (name,idsubdgect,hardnest,time) value (?, ?, ?, ?) ";
    private static final String SQL__GET_TEST_BY_ID_RU = " SELECT * FROM test INNER JOIN en ON idsubdgect = idsub WHERE id=?";
    private static final String SQL__GET_TEST_BY_ID_EN = " SELECT * FROM test INNER JOIN en ON idsubdgect = idsub WHERE id=?";
    private static final String SQL__GET_NAME_BY_ID = "SELECT name FROM test WHERE id = ?";
    private static final String SQL__SEARCH_BY_NAME_EN = "SELECT * FROM test INNER JOIN en ON idsubdgect = idsub WHERE name LIKE ?";
    private static final String SQL__SEARCH_BY_NAME_RU = "SELECT * FROM test INNER JOIN ru ON idsubdgect = idsub WHERE name LIKE ?";
    private static final String SQL__GET_ALL_BY_SUBJECT_RU = "SELECT * FROM test INNER JOIN ru ON idsubdgect = idsub WHERE ru.subdgect = ?";
    private static final String SQL__GET_ALL_BY_SUBJECT_EN = "SELECT * FROM test INNER JOIN en ON idsubdgect = idsub WHERE en.subdgect =  ?";
    private static final String SQL__SORT_BY_VALUE_EN = "SELECT * FROM  test INNER JOIN en ON idsubdgect = idsub ORDER BY";
    private static final String SQL__SORT_BY_VALUE_RU = "SELECT * FROM  test INNER JOIN ru ON idsubdgect = idsub ORDER BY";
    private static final String SQL__GET_TIME_BY_ID = "SELECT time FROM test WHERE id = ?";
    private static final String SQL__UPDATE_BY_ID = "UPDATE test SET name = ?, idsubdgect = ?, hardnest = ?, time = ? WHERE id =?";
    private static final String SQL__DELETE_DY_ID = "DELETE FROM test WHERE id = ?";
    private static final String SQL__GET_SUBJECT_EN = "SELECT * FROM en";
    private static final String SQL__GET_SUBJECT_RU = "SELECT * FROM ru";
    private static final String SQL__GET_COUNT_OF_TESTS ="SELECT COUNT(1) AS count FROM test";
    private static final String SQL__GET_COUNT_OF_TESTS_WHERE ="SELECT COUNT(1) AS count FROM test";
    private static final String SQL__WHERE_NAME_LIKE = "WHERE  name LIKE ?";
    private static final String SQL__WHERE_SUB = "SELECT COUNT(1) AS count FROM test INNER JOIN ru ON idsubdgect = idsub WHERE ru.subdgect = ?";

    public ArrayList<Subject> getSubjectAdd(String lang) {
        ArrayList<Subject> ret = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__GET_SUBJECT_RU);
            else
                pstm = con.prepareStatement(SQL__GET_SUBJECT_EN);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt("idsub"));
                subject.setSubject(rs.getString("subdgect"));
                ret.add(subject);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    public int getCountOfTestsWhereSub(String sub){
        int ret = 0;
        List<Results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__WHERE_SUB);
            prst.setString(1, sub);
            rs = prst.executeQuery();
            if (rs.next())
                ret = rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    public int getCountOfTestsWhereName(String name){
        int ret = 0;
        List<Results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_COUNT_OF_TESTS_WHERE + SQL__WHERE_NAME_LIKE);
            prst.setString(1, "%" + name + "%");
            rs = prst.executeQuery();
            if (rs.next())
                ret = rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    public int getCountOfTests(){
        int ret = 0;
        List<Results> retlist = new ArrayList<>();
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            prst = con.prepareStatement(SQL__GET_COUNT_OF_TESTS);

            rs = prst.executeQuery();
            if (rs.next())
                ret = rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    public ArrayList<Test> getSubject(String lang) {
        ArrayList<Test> ret = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__GET_SUBJECT_RU);
            else
                pstm = con.prepareStatement(SQL__GET_SUBJECT_EN);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Test test = new Test();
                test.setSubdgect(rs.getString("subdgect"));

                ret.add(test);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }


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
            DBManager.getInstance().rollbackAndClose(con);
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
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }
    }

    public int getTimeById(int id) {
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
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }

        return time;
    }

    public ArrayList<Test> sortByValue(String search, String lang, int page) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Test> retlist = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__SORT_BY_VALUE_RU + " " + search + SQL__LIMIT);
            else
                pstm = con.prepareStatement(SQL__SORT_BY_VALUE_EN + " " + search + SQL__LIMIT);
            pstm.setInt(1,page);
            rs = pstm.executeQuery();
            testMapper mapper = new testMapper();
            while (rs.next())
                retlist.add(mapper.mapRow(rs));
            con.close();

        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }

        return retlist;
    }

    public ArrayList<Test> getListOfTestsWithSubdgect(String name, String lang, int page) {
        ArrayList<Test> retlist = new ArrayList<Test>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__GET_ALL_BY_SUBJECT_RU + SQL__LIMIT);
            else
                pstm = con.prepareStatement(SQL__GET_ALL_BY_SUBJECT_EN + SQL__LIMIT);


            pstm.setString(1, name);
            pstm.setInt(2,page);
            rs = pstm.executeQuery();
            Test test = new Test();
            testMapper mapper = new testMapper();
            while (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }
            con.close();

        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }

        return retlist;
    }


    public ArrayList<Test> getListByName(String name, String lang, int page) {
        ArrayList<Test> retlist = new ArrayList<Test>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__SEARCH_BY_NAME_RU + SQL__LIMIT);
            else
                pstm = con.prepareStatement(SQL__SEARCH_BY_NAME_EN + SQL__LIMIT);
            pstm.setString(1, "%" + name + "%");
            pstm.setInt(2,page);
            rs = pstm.executeQuery();
            testMapper mapper = new testMapper();
            Test test = new Test();
            while (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }
            con.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }

        return retlist;
    }

    public String getTextById(int id) {
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
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }


        return text;
    }

    public ArrayList<Test> getTestsById(int id, String lang) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Test> retlist = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__GET_TEST_BY_ID_RU);
            else
                pstm = con.prepareStatement(SQL__GET_TEST_BY_ID_EN);
            pstm.setInt(1, id);
            testMapper mapper = new testMapper();
            System.out.println(pstm);
            rs = pstm.executeQuery();
            if (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }
        return retlist;
    }

    public void setTest(String name, String subdgect, int hardnest, int time) {
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
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }

    }


    public int getIdByName(String name) {
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
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        }
        return id;
    }

    public ArrayList<Test> getListOfTests(String lang, int page) {
        ArrayList<Test> retlist = new ArrayList<Test>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            if (lang.equals("ru"))
                pstm = con.prepareStatement(SQL__GET_ALL_TESTS + SQL_SELECT_LANG_RU + SQL__LIMIT);
            else
                pstm = con.prepareStatement(SQL__GET_ALL_TESTS + SQL_SELECT_LANG_EN + SQL__LIMIT);
            pstm.setInt(1,page);
            rs = pstm.executeQuery();
            testMapper mapper = new testMapper();
            Test test = new Test();
            while (rs.next()) {
                retlist.add(mapper.mapRow(rs));
            }

            con.close();

        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(con);
            throwables.printStackTrace();
        } finally {

        }

        return retlist;
    }

    private static class testMapper implements EntityMapper<Test> {
        @Override
        public Test mapRow(ResultSet rs) {

            try {
                Test test = new Test();
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

