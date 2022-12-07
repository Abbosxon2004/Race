
import com.example.race.beans.User;
import com.example.race.beans.enums.Roles;
import com.example.race.dao.ApplicationDao;
import com.example.race.dao.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterAndLoginTest {
    static ApplicationDao dao;

    private static void testRegistration(final String fullName, final String username, final String password, final int resultMessage) {
        dao = new ApplicationDao();

        User user = new User();
        user.setUsername(username);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setRole(Roles.USER.name());
        user.setBalance(10000);
        int success = dao.registerUser(user) == 0 ? 0 : 1;

        assertEquals(resultMessage, success);
    }

    public static String getUserFromDB() {
        try {
            String username = null;
            Connection connection = DBConnection.getConnectionToDatabase();
            String query = "SELECT * FROM users ORDER BY random() LIMIT 1";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("username");
            }
            connection.close();
            return username;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUser(String username) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String query = "delete FROM users where username=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void sleepMode() throws InterruptedException {
        Thread.sleep(50);
        // Just more time and enjoying
        // 😊😊😊😊😊😊😊
    }

    @Test
    public void existUser001() {
        String username = getUserFromDB();
        testRegistration(username + " person", username, "1111", 0);
    }

    @Test
    public void existUser002() {
        String username = getUserFromDB();
        testRegistration(username + " person", username, "123", 0);
    }

    @Test
    public void existUser003() {
        String username = getUserFromDB();
        testRegistration(username + " person", username, "789456", 0);
    }

    @Test
    public void existUser004() {
        String username = getUserFromDB();
        testRegistration(username + " person", username, "45678", 0);
    }

    @Test
    public void existUser005() {
        String username = getUserFromDB();
        testRegistration(username + " person", username, "1594", 0);
    }


    @Test
    public void registerUser001() throws IOException, URISyntaxException {
        String username = "user_1";
        testRegistration(username + " person", username, "user_1", 1);
        deleteUser(username);
        System.out.println("user registering tested successfully and user is deleted");
    }

    @Test
    public void registerUser002() throws IOException, URISyntaxException {
        String username = "user_2";
        testRegistration(username + " person", username, "user_2", 1);
        deleteUser(username);
        System.out.println("user registering tested successfully and user is deleted");
    }

    @Test
    public void registerUser003() throws IOException, URISyntaxException {
        String username = "user_3";
        testRegistration(username + " person", username, "user_3", 1);
        deleteUser(username);
        System.out.println("user registering tested successfully and user is deleted");
    }

    @Test
    public void registerUser004() throws IOException, URISyntaxException {
        String username = "user_4";
        testRegistration(username + " person", username, "user_4", 1);
        deleteUser(username);
        System.out.println("user registering tested successfully and user is deleted");
    }

    @Test
    public void registerUser005() throws IOException, URISyntaxException {
        String username = "user_5";
        testRegistration(username + " person", username, "user_5", 1);
        deleteUser(username);
        System.out.println("user registering tested successfully and user is deleted");
    }


    private static void testLogin(final String username, final String password, final boolean result) {
        dao = new ApplicationDao();
        boolean success = dao.validateUser(username, password);
        assertEquals(result, success);
    }

    public static String getPasswordByUsernameFromDB(String username) {
        try {
            String password = null;
            Connection connection = DBConnection.getConnectionToDatabase();
            String query = "SELECT password FROM users where username=?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
            connection.close();
            return password;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canLoginUser001() {
        String username = getUserFromDB();
        testLogin(username, getPasswordByUsernameFromDB(username), true);
    }

    @Test
    public void canLoginUser002() {
        String username = getUserFromDB();
        testLogin(username, getPasswordByUsernameFromDB(username), true);
    }

    @Test
    public void canLoginUser003() {
        String username = getUserFromDB();
        testLogin(username, getPasswordByUsernameFromDB(username), true);
    }

    @Test
    public void canLoginUser004() {
        String username = getUserFromDB();
        testLogin(username, getPasswordByUsernameFromDB(username), true);
    }

    @Test
    public void canLoginUser005() {
        String username = getUserFromDB();
        testLogin(username, getPasswordByUsernameFromDB(username), true);
    }


    @Test
    public void canNotLoginUser001() {
        String username = getUserFromDB();
        testLogin(getUserFromDB(), getPasswordByUsernameFromDB(username), false);
    }

    @Test
    public void canNotLoginUser002() {
        String username = getUserFromDB();
        testLogin(getUserFromDB(), getPasswordByUsernameFromDB(username), false);
    }

    @Test
    public void canNotLoginUser003() {
        String username = getUserFromDB();
        testLogin(getUserFromDB(), getPasswordByUsernameFromDB(username), false);
    }

    @Test
    public void canNotLoginUser004() {
        String username = getUserFromDB();
        testLogin(getUserFromDB(), getPasswordByUsernameFromDB(username), false);
    }

    @Test
    public void canNotLoginUser005() {
        String username = getUserFromDB();
        testLogin(getUserFromDB(), getPasswordByUsernameFromDB(username), false);
    }
}
