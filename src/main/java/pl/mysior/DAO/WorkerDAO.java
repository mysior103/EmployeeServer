package pl.mysior.DAO;

import pl.mysior.BuisnessObject.ConnectionFactory;
import pl.mysior.BuisnessObject.Dealer;
import pl.mysior.BuisnessObject.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO {

    public List<Object> getAllWorkers() {
        List<Dealer> dealers = new DealerDAO().getAllDealers();
        List<Director> directors = new DirectorDAO().getAllDirectors();
        List<Object> allWorkers = new ArrayList<>();
        allWorkers.addAll(dealers);
        allWorkers.addAll(directors);
        return allWorkers;
    }
}