package pl.mysior;

import javax.xml.stream.events.EndDocument;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;

public class Main {
    public static void main(String[] args) {

        try {
            Endpoint.publish("http://localhost:4321/workers", new AllWorkersImpl());
            int i = 1;
            int port = 1234;
            LoggingInterface logging = new LoggingInterfaceImpl(AuthType.LOCAL);
            Naming.rebind("Elo", logging);
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fatal Error!");
        }

    }
}