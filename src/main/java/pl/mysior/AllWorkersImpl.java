package pl.mysior;
import pl.mysior.BuisnessObject.Dealer;
import pl.mysior.BuisnessObject.Director;
import pl.mysior.DAO.DealerDAO;
import pl.mysior.DAO.DirectorDAO;

import javax.jws.WebService;
import java.util.List;
@WebService(endpointInterface = "pl.mysior.AllWorkers")
public class AllWorkersImpl implements AllWorkers {
    @Override
    public List<Dealer> getAllDealers() {
        DealerDAO dealerDAO = new DealerDAO();
        return dealerDAO.getAllDealers();

    }

    @Override
    public List<Director> getAllDirectors() {
        DirectorDAO directorDAO = new DirectorDAO();
        return directorDAO.getAllDirectors();
    }
}
