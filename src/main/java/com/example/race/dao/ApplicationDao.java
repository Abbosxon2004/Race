package com.example.race.dao;


import com.example.race.beans.*;
import com.example.race.classes.UniqueRng;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ApplicationDao {

    public int registerUser(User user) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "insert into users(id,full_name,username,password,role,balance) values(?,?,?,?,?,?)";

            // set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, getNextId("users"));
            statement.setString(2, user.getFullName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getBalance());


            // execute the statement
            rowsAffected = statement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public boolean validateUser(String username, String password) {
        boolean isValidUser = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from users where username=? and password=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                isValidUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return isValidUser;
    }

    public User getProfileDetails(String username) {
        User user = null;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from users where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setFullName(resultSet.getString("full_name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setBalance(resultSet.getInt("balance"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    public static int getNextId(String table) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String query = "select max(id) from " + table;

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<History> getHistories(String username) {
        List<History> historyList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from history where user_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, getUserId(username));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                History history = new History();
                history.setUser(username);
                history.setRace(getRaceName(resultSet.getInt("race_id")));
                history.setWinMoney(resultSet.getInt("win_money"));
                historyList.add(history);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return historyList;
    }

    public static int getUserId(String username) {
        int id = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from users where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    public static int getRaceId(String race) {
        int id = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from races where name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, race);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    public static int getHorseId(String horse) {
        int id = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from horses where name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, horse);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    public static String getRaceName(int id) {
        String raceName = "";
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from races where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                raceName = resultSet.getString("name");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return raceName;
    }

    public static String getHorseName(int id) {
        String horseName = "";
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from horses where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                horseName = resultSet.getString("name");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return horseName;
    }

    public void createRace(Races races) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "insert into races(id,name,start_date,betting_deadline_date,status) values(?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, getNextId("races"));
            statement.setString(2, races.getName());
            statement.setDate(3, races.getStartDate());
            statement.setDate(4, races.getBettingDeadlineDate());
            statement.setBoolean(5, true);

            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean existsUser(String username) {
        boolean exists = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String query = "select * from users where username=?";

            // set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String ism = null;
            while (resultSet.next()) {
                ism = resultSet.getString("username");
            }
            if (ism != null) {
                exists = true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return exists;
    }

    public String getRoleByUsername(String username) {
        String role = "";
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String query = "select * from users where username=?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("role");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return role;
    }

    public boolean bettingMoney(String fromUsername, String race, Integer bettingMoney, String toHorseName, String horse_position) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from users where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fromUsername);

            ResultSet resultSet = statement.executeQuery();
            int balance = 0;
            while (resultSet.next()) {
                balance = resultSet.getInt("balance");
            }
            if (balance < bettingMoney)
                return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "insert into betting_race(id,user_id,race_id,horse_id,betting_money,horse_position) values(?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, getNextId("betting_race"));
            statement.setInt(2, getUserId(fromUsername));
            statement.setInt(3, getRaceId(race));
            statement.setInt(4, getHorseId(toHorseName));
            statement.setInt(5, bettingMoney);
            statement.setString(6, horse_position);

            rowsAffected = statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (rowsAffected > 0) {
            try {
                Connection connection = DBConnection.getConnectionToDatabase();

                String sql = "UPDATE users SET balance=balance-? WHERE username=?;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, bettingMoney);
                statement.setString(2, fromUsername);
                statement.execute();

                sql = "UPDATE users SET balance=balance+? WHERE role='ADMIN';";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, bettingMoney);
                statement.execute();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public List<Races> getOpenRaces() {

        List<Races> racesList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from races where status=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Races race = new Races();
                race.setName(resultSet.getString("name"));
                race.setStartDate(resultSet.getDate("start_date"));
                racesList.add(race);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return racesList;
    }

    public List<Races> getGeneratedRaces() {

        List<Races> racesList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from races where status=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Races race = new Races();
                race.setId(resultSet.getInt("id"));
                race.setName(resultSet.getString("name"));
                race.setStartDate(resultSet.getDate("start_date"));
                racesList.add(race);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return racesList;
    }

    public List<Horses> getHorseList() {
        List<Horses> horsesList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from horses";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Horses horse = new Horses();
                horse.setId(resultSet.getInt("id"));
                horse.setName(resultSet.getString("name"));
                horsesList.add(horse);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return horsesList;
    }

    public boolean setCoefficient(String race_name, String horse_name, int coefficient) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "insert into book_making(id,race_id,horse_id,coefficient_degree) values(?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, getNextId("book_making"));
            statement.setInt(2, getRaceId(race_name));
            statement.setInt(3, getHorseId(horse_name));
            statement.setInt(4, coefficient);

            rowsAffected = statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (rowsAffected > 0)
            return true;
        else return false;
    }

    public Map<String, List<RaceResults>> getRacesResults() {
        Map<String, List<RaceResults>> resultsMap = new HashMap<>();
        List<Races> generatedRaces = getGeneratedRaces();
        List<Horses> horseList = getHorseList();
        Connection connection = DBConnection.getConnectionToDatabase();
        for (Races race : generatedRaces) {
            List<RaceResults> list = new ArrayList<>();
            try {
                String sql = "select * from race_results where race_id=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, race.getId());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    RaceResults raceResults = new RaceResults();
                    raceResults.setRace_name(race.getName());
                    raceResults.setHorse_name(horseList.get(resultSet.getInt("horse_id") - 1).getName());
                    raceResults.setPosition(resultSet.getInt("position"));
                    list.add(raceResults);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            resultsMap.put(race.getName(), list);
        }
        return resultsMap;
    }


    public boolean generateRace(String race_name) {
        boolean status = true;
        int rowsAffected = 0;
        List<Horses> horseList = getHorseList();
        List<Integer> positionList = new ArrayList<Integer>();
        UniqueRng rng = new UniqueRng(10);
        Connection connection = DBConnection.getConnectionToDatabase();
        int race_id = getRaceId(race_name);
        try {
            for (Horses horse : horseList) {

                int horse_position = rng.next();
                System.out.print(horse_position + " ");

                String insertQuery = "insert into race_results(id,race_id,horse_id,position) values(?,?,?,?)";

                PreparedStatement statement = connection.prepareStatement(insertQuery);
                statement.setInt(1, getNextId("race_results"));
                statement.setInt(2, race_id);
                statement.setInt(3, horse.getId());
                statement.setInt(4, horse_position);

                rowsAffected = statement.executeUpdate();
            }
            if (rowsAffected == 0) {
                status = false;
            }
        } catch (SQLException exception) {
            try {
                connection = DBConnection.getConnectionToDatabase();
                String query = "DELETE FROM race_results WHERE race_id=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, race_id);
                statement.execute();
                connection.close();
            } catch (SQLException exception1) {
                exception1.printStackTrace();
            }
            return false;
        }
        moneyTransformation(race_name);
        changeRaceStatus(race_name);
        return status;
    }


    public void changeRaceStatus(String race_name) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String query = "update races set status=? where name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setString(2, race_name);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void moneyTransformation(String race_name) {
        List<MoneyTranferDto> tranferList = new ArrayList<>();
        List<Horses> horseList = getHorseList();
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "select distinct races.id, horses.id, position,betting_race.horse_position,betting_race.betting_money,betting_race.user_id\n" +
                    "from race_results\n" +
                    "         inner join races on (race_results.race_id = races.id)\n" +
                    "         inner join horses on (race_results.horse_id = horses.id)\n" +
                    "         inner join betting_race on (races.id = betting_race.race_id and betting_race.horse_id = horses.id)\n" +
                    "where races.name=?;";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, race_name);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MoneyTranferDto tranferDto = new MoneyTranferDto();
                tranferDto.setUser_id(resultSet.getInt("user_id"));
                tranferDto.setRace_id(resultSet.getInt("races_id"));
                tranferDto.setHorse_id(resultSet.getInt("horses_id"));
                tranferDto.setBetting_money(resultSet.getInt("betting_money"));
                tranferDto.setResult_position(resultSet.getInt("position"));
                tranferDto.setBet_position(resultSet.getString("horse_position"));
                tranferList.add(tranferDto);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (Horses horses : horseList) {
            map.put(horses.getId(), 2);
        }

        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "select race_id,horse_id,coefficient_degree from book_making\n" +
                    "inner join races on (book_making.race_id = races.id)\n" +
                    "where races.name=?";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, race_name);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int horse_id=resultSet.getInt("horse_id");
                map.remove(horse_id);
                map.put(horse_id,resultSet.getInt("coefficient_degree"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        for (MoneyTranferDto tranferDto : tranferList) {
            if (tranferDto.getBet_position().equals("first_three_winners")){
                if (tranferDto.getResult_position()==1||tranferDto.getResult_position()==3||tranferDto.getResult_position()==3){
                    sendMoney(tranferDto.getUser_id(),map.get(tranferDto.getHorse_id())*tranferDto.getBetting_money());
                }
            }else {
                if (tranferDto.getResult_position()==Integer.valueOf(tranferDto.getBet_position())){
                    sendMoney(tranferDto.getUser_id(),map.get(tranferDto.getHorse_id())*tranferDto.getBetting_money());
                }
            }
        }
    }

    public void sendMoney(Integer from_user_id,Integer moneyAmount){
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String insertQuery = "select race_id,horse_id,coefficient_degree from book_making\n" +
                    "inner join races on (book_making.race_id = races.id)\n" +
                    "where races.name=?";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, race_name);


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
