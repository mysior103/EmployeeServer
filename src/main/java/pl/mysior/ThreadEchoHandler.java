package pl.mysior;

import pl.mysior.DAO.WorkerDAO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ThreadEchoHandler implements Runnable {
    private Socket incoming;

    ThreadEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream())) {

            oos.writeObject(getAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized List<Object> getAll() {
        WorkerDAO workerDAO = new WorkerDAO();
        return workerDAO.getAllWorkers();
    }
}
