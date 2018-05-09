package pl.mysior.Logging;

import pl.mysior.BuisnessObject.User;
import pl.mysior.DAO.UserDAO;
import pl.mysior.Main;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class LoggingInterfaceImpl extends UnicastRemoteObject implements LoggingInterface {
    protected LoggingInterfaceImpl() throws RemoteException {
    }
    private static int randInteger;
    private AuthType authType;

    public LoggingInterfaceImpl(AuthType authType) throws RemoteException{
        this.authType = authType;
    }

    @Override
    public String checkAccess(String userName, String password) throws RemoteException {
        String result = null;
        if (authType==AuthType.ZUT) {
            try {
                String wipsadUsername = "wipsad\\" + userName;
                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.PROVIDER_URL, "ldap://82.145.72.13:389");
                env.put(Context.SECURITY_AUTHENTICATION, "simple");
                env.put(Context.SECURITY_PRINCIPAL, wipsadUsername);
                env.put(Context.SECURITY_CREDENTIALS, password);
                DirContext ctx = new InitialDirContext(env);
                if(ctx!=null) result = generateKey(userName);

            } catch (AuthenticationException authEx) {
                result = null;
            } catch (NamingException e) {
                e.printStackTrace();
            }
        } else {
            for (User u : getUserList()) {
                if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                    result = generateKey(userName);
                } else {
                    System.out.println("Failed to authorize!");
                }
            }
        }
        return result;
    }

    private String generateKey(String username) {
        randInteger = randomWithRange(0, 10000);
        String preparedToEncoded = username + randInteger;
        byte[] encodedBytes = Base64.getEncoder().encode(preparedToEncoded.getBytes());
        String generatedKey = new String(encodedBytes);
        Main.synchronizedAuthKeySet.add(generatedKey);
        System.out.println("Created new Auth Key : " + generatedKey);
        return generatedKey;
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    private List<User> getUserList() {
        UserDAO userDAO = new UserDAO();
        return userDAO.getAllUsers();
    }
}
