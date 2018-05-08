package pl.mysior;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;

public class Main {
    public static void main(String[] args) {

        try {
            LoggingInterface logging = new LoggingImpl();
            Naming.rebind("Elo", logging);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int i = 1;
            int port = 1234;
            ServerSocket s = new ServerSocket(port);
            System.out.println("Server started on " + port + " port.");
            System.out.println("Server is ready!");
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}