package pl.mysior;

import pl.mysior.BuisnessObject.Dealer;
import pl.mysior.BuisnessObject.Director;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
@WebService
public interface AllWorkers {
    @WebMethod
    @WebResult(name = "dealer")
    List<Dealer> getAllDealers();

    @WebMethod
    @WebResult(name = "director")
    List<Director> getAllDirectors();
}
