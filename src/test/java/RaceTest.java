import com.example.race.beans.Horses;
import com.example.race.beans.Races;
import com.example.race.beans.User;
import com.example.race.dao.ApplicationDao;
import com.example.race.dao.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RaceTest {
    ApplicationDao dao;

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

    public void createRace(String name, int result) {
        dao = new ApplicationDao();
        Races race = new Races();
        race.setName(name);
        race.setStartDate(new Date(System.currentTimeMillis()));
        race.setBettingDeadlineDate(new Date((long) (System.currentTimeMillis() - 8.64e+7)));
        int success = dao.createRace(race) > 0 ? 1 : 0;
        assertEquals(result, success);
    }

    public static void deleteRace(String name) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String query = "delete from races where name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
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
    public void createRace001() {
        String race_name = "race_1";
        createRace(race_name, 1);
        deleteRace(race_name);
    }

    @Test
    public void createRace002() {
        String race_name = "race_2";
        createRace(race_name, 1);
        deleteRace(race_name);
    }

    @Test
    public void createRace003() {
        String race_name = "race_3";
        createRace(race_name, 1);
        deleteRace(race_name);
    }

    @Test
    public void createRace004() {
        String race_name = "race_4";
        createRace(race_name, 1);
        deleteRace(race_name);
    }

    @Test
    public void createRace005() {
        String race_name = "race_5";
        createRace(race_name, 1);
        deleteRace(race_name);
    }

    @Test
    public void checkHorsesNumber() {
        dao = new ApplicationDao();
        List<Horses> horseList = dao.getHorseList();
        int numberHorses = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select count(id) from horses";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numberHorses = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        assertEquals(numberHorses, horseList.size());
    }


    @Test
    public void checkGeneratedRacesList() {
        dao = new ApplicationDao();
        List<Races> generatedRaces = dao.getGeneratedRaces();

        List<String> nameList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from races where status=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nameList.add(resultSet.getString("name"));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        for (Races generatedRace : generatedRaces) {
            assertTrue(nameList.contains(generatedRace.getName()));
        }
    }

    @Test
    public void checkOpenRacesList() {
        dao = new ApplicationDao();
        List<Races> openRaces = dao.getOpenRaces();

        List<String> nameList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from races where status=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nameList.add(resultSet.getString("name"));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        for (Races generatedRace : openRaces) {
            assertTrue(nameList.contains(generatedRace.getName()));
        }
    }

    @Test
    public void checkBalanceChanging_1() {
        dao = new ApplicationDao();
        String username = getUserFromDB();
        User profileDetails = dao.getProfileDetails(username);
        int previousBalance = profileDetails.getBalance();
        int randomMoney = (int) (Math.random() * 10000);
        dao.changeUserBalance(username, randomMoney);
        profileDetails = dao.getProfileDetails(username);
        assertEquals(profileDetails.getBalance(), previousBalance + randomMoney);
        dao.changeUserBalance(username, -1 * randomMoney);
    }

    @Test
    public void checkBalanceChanging_2() {
        dao = new ApplicationDao();
        String username = getUserFromDB();
        User profileDetails = dao.getProfileDetails(username);
        int previousBalance = profileDetails.getBalance();
        int randomMoney = (int) (Math.random() * 10000);
        dao.changeUserBalance(username, randomMoney);
        profileDetails = dao.getProfileDetails(username);
        assertEquals(profileDetails.getBalance(), previousBalance + randomMoney);
        dao.changeUserBalance(username, -1 * randomMoney);
    }

    @Test
    public void checkBalanceChanging_3() {
        dao = new ApplicationDao();
        String username = getUserFromDB();
        User profileDetails = dao.getProfileDetails(username);
        int previousBalance = profileDetails.getBalance();
        int randomMoney = (int) (Math.random() * 10000);
        dao.changeUserBalance(username, randomMoney);
        profileDetails = dao.getProfileDetails(username);
        assertEquals(profileDetails.getBalance(), previousBalance + randomMoney);
        dao.changeUserBalance(username, -1 * randomMoney);
    }

    @Test
    public void checkBalanceChanging_4() {
        dao = new ApplicationDao();
        String username = getUserFromDB();
        User profileDetails = dao.getProfileDetails(username);
        int previousBalance = profileDetails.getBalance();
        int randomMoney = (int) (Math.random() * 10000);
        dao.changeUserBalance(username, randomMoney);
        profileDetails = dao.getProfileDetails(username);
        assertEquals(profileDetails.getBalance(), previousBalance + randomMoney);
        dao.changeUserBalance(username, -1 * randomMoney);
    }

    @Test
    public void checkBalanceChanging_5() {
        dao = new ApplicationDao();
        String username = getUserFromDB();
        User profileDetails = dao.getProfileDetails(username);
        int previousBalance = profileDetails.getBalance();
        int randomMoney = (int) (Math.random() * 10000);
        dao.changeUserBalance(username, randomMoney);
        profileDetails = dao.getProfileDetails(username);
        assertEquals(profileDetails.getBalance(), previousBalance + randomMoney);
        dao.changeUserBalance(username, -1 * randomMoney);
    }

    @Test
    public void checkUsersNumber(){
        dao = new ApplicationDao();
        List<User> userList = dao.getAllUsersForAdmin();
        int numberUsers = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select count(id) from users";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numberUsers = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        assertEquals(numberUsers, userList.size());
    }

}
