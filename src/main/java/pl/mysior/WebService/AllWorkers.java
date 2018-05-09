package pl.mysior.WebService;

import pl.mysior.BuisnessObject.Dealer;
import pl.mysior.BuisnessObject.Director;
import pl.mysior.BuisnessObject.Worker;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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

    @WebMethod
    @WebResult(name = "workers")
    List<Object> getAllWorkers(@WebParam(name = "authKey")String authKey);
}
