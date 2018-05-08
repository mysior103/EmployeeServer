package pl.mysior.DAO;

import pl.mysior.BuisnessObject.ConnectionFactory;
import pl.mysior.BuisnessObject.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String query = "SELECT * FROM users";
        try(Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ResultSet resultSet = ptmt.executeQuery())
        {
            while (resultSet.next()) {
                User user = new User();
                user.setIdusers(resultSet.getInt("iduser"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
