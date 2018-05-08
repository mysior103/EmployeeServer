package pl.mysior.DAO;

import pl.mysior.BuisnessObject.ConnectionFactory;
import pl.mysior.BuisnessObject.Dealer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealerDAO extends WorkerDAO {

    public List<Dealer> getAllDealers() {
        List<Dealer> allDealers = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE position='Handlowiec'";
        try(Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ResultSet resultSet = ptmt.executeQuery())
        {
            while (resultSet.next()) {
                Dealer dealer = new Dealer();
                dealer.setID(resultSet.getLong("id"));
                dealer.setName(resultSet.getString("name"));
                dealer.setLastName(resultSet.getString("lastName"));
                dealer.setPosition(resultSet.getString("position"));
                dealer.setSalary(resultSet.getBigDecimal("salary"));
                dealer.setPhoneNumber(resultSet.getString("phoneNumber"));
                dealer.setCommission(resultSet.getBigDecimal("commission"));
                dealer.setMaxCommission(resultSet.getBigDecimal("maxCommission"));
                allDealers.add(dealer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDealers;
    }
}
