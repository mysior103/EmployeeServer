package pl.mysior.DAO;

import pl.mysior.BuisnessObject.ConnectionFactory;
import pl.mysior.BuisnessObject.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO extends WorkerDAO{

    public DirectorDAO() {}


    public List<Director> getAllDirectors() {
        List<Director> allDirectors = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE position='Dyrektor'";

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ptmt = conn.prepareStatement(query);
             ResultSet resultSet = ptmt.executeQuery())
        {
            while (resultSet.next()) {
                Director director = new Director();
                director.setID(resultSet.getLong("id"));
                director.setName(resultSet.getString("name"));
                director.setLastName(resultSet.getString("lastName"));
                director.setPosition(resultSet.getString("position"));
                director.setSalary(resultSet.getBigDecimal("salary"));
                director.setPhoneNumber(resultSet.getString("phoneNumber"));
                director.setCreditCardNumber(resultSet.getString("creditCardNumber"));
                director.setCostLimit(resultSet.getBigDecimal("costLimit"));
                allDirectors.add(director);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDirectors;
    }
}
