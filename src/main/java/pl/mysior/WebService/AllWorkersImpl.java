package pl.mysior.WebService;

import pl.mysior.BuisnessObject.Dealer;
import pl.mysior.BuisnessObject.Director;
import pl.mysior.DAO.DealerDAO;
import pl.mysior.DAO.DirectorDAO;
import pl.mysior.DAO.WorkerDAO;
import pl.mysior.Logging.KeySearcher;

import javax.jws.WebService;
import java.util.List;
import static pl.mysior.Main.*;

@WebService(endpointInterface = "pl.mysior.WebService.AllWorkers")
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

    @Override
    public List<Object> getAllWorkers(String authKey) {
        WorkerDAO workerDAO = new WorkerDAO();
        if (KeySearcher.search(authKey)) {
            synchronizedAuthKeySet.remove(authKey);
            return workerDAO.getAllWorkers();
        } else {
            return null;
        }
    }

}
