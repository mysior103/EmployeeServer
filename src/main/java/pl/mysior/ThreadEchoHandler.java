package pl.mysior;

import pl.mysior.DAO.WorkerDAO;
import pl.mysior.Logging.KeySearcher;

import java.io.*;
import java.net.Socket;
import java.util.List;
import static pl.mysior.Main.*;

public class ThreadEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    public void run() {
        try (DataInputStream din = new DataInputStream(incoming.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream())) {
            String gotAuthKey = din.readUTF();
            boolean authorized = KeySearcher.search(gotAuthKey);
            if (authorized) {
                oos.writeObject(getAll());
                synchronizedAuthKeySet.remove(gotAuthKey);
            } else {
                System.out.println("Auth failed!");
                oos.writeObject(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private synchronized List<Object> getAll() {
        WorkerDAO workerDAO = new WorkerDAO();
        return workerDAO.getAllWorkers();
    }
}