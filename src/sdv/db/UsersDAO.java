package sdv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sdv.model.Player;


public class UsersDAO {
	public UsersDAO() {
    }

    public static List<Player> getUsersListFromDB() {
        List<Player> usersList = new ArrayList<>();
        String query = "SELECT * FROM `users`";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = DAO.getDataSource().getConnection();
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int player_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Player player = new Player(player_id, username, password);
                usersList.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public static Player getUserFromDbIfCredentialsAreValid(String userName, String userPassword) {
        Player player = null;
        String query = "SELECT * FROM `users` WHERE `username` = '" + userName + "' AND `password` = '" + userPassword + "'";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = DAO.getDataSource().getConnection();
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs != null && rs.next()) {
                int player_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                player = new Player(player_id, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }
    
    public static boolean addUserToDb(String userName, String userPassword) {
    		String query = "INSERT INTO `users` (username, password) VALUES (\'" + userName + "\', \'" + userPassword + "\')";
    		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = DAO.getDataSource().getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
