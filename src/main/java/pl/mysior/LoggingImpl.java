package pl.mysior;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

public class LoggingImpl extends UnicastRemoteObject implements LoggingInterface {
    LoggingImpl() throws RemoteException {
    }

    private String userName = "user";
    private String password = "pass";

    public static int getRandInteger() {
        return randInteger;
    }

    private static int randInteger;

    @Override
    public String chceckAccess(String userName, String password) throws RemoteException {
        if (userName.equals(this.userName) && password.equals(this.password)) {
            randInteger = randomWithRange(0, 10000);
            String preparedString = userName + password + randInteger;
            byte[] encodedBytes = Base64.getEncoder().encode(preparedString.getBytes());
            return new String(encodedBytes);
        }
        return null;
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
