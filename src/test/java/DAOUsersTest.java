import db.DAOUsers;
import db.DBManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;


import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class DAOUsersTest {
//
    @InjectMocks
    private DAOUsers daoUsers = new DAOUsers();

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet result;


    @Test
    public void test() throws SQLException {

        Mockito.when(connection.prepareStatement("INSERT users (login, password, role , name, surname, patronymic) VALUE (?, ?, ?, ?, ?, ?)")).thenReturn(statement);

        Mockito.when(daoUsers.addUser("w","w","w","stud","s","s")).thenReturn(true);

        Mockito.verify(statement).executeUpdate();
    }


}
