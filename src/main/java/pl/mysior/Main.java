package pl.mysior;

import pl.mysior.Logging.AuthType;
import pl.mysior.Logging.LoggingInterface;
import pl.mysior.Logging.LoggingInterfaceImpl;
import pl.mysior.WebService.AllWorkersImpl;

import javax.xml.ws.Endpoint;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static HashSet<String> authKeySet = new HashSet();
    public static Set<String> synchronizedAuthKeySet = Collections.synchronizedSet(authKeySet);
    public static void main(String[] args) {
        //Enable logs
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        try {
            //Service
            Endpoint.publish("http://localhost:4321/workers", new AllWorkersImpl());

            //RMI
            LoggingInterface logging = new LoggingInterfaceImpl(AuthType.LOCAL);
            Naming.rebind("Elo", logging);


            //Server
            int i = 1;
            int port = 1234;
            ServerSocket s = new ServerSocket(port);
            System.out.println("Server started on " + port + " port.");
            System.out.println("Server is ready!");
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Connection " + i);
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