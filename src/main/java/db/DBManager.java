package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {


    private static final String URL = "jdbc:mysql://localhost:3306/Test";
    private static final String USER = "root";
    private static final String PASSWORD = "ax0271ac";
    private static final String FULLURL = URL + "?" + "User=" + USER + "&password=" + PASSWORD;
    private static DBManager instance;


    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }


    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");

            // ST4DB - the name of data source
            DataSource ds = (DataSource) envContext.lookup("jdbc/test");
            con = ds.getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnectionWithDriverManager() throws SQLException {

        Connection connection = DriverManager
                .getConnection(FULLURL);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }
}
