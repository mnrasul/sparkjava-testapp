package ca.rasul.sparkjava.testapp.services;

import ca.rasul.sparkjava.testapp.models.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nrasul on 9/28/14.
 */
public class UserService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    /**
     * Basic authentication service, accepts any username as long as one doesn't exist before
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password){
        //make a db call to see if password is a match
        //we'll assume it is
        users.putIfAbsent(username, new User(username,password));
        return true;
    }

    public void logout(String username){
        users.remove(users.get(username));
    }

    public boolean isLoggedIn(String username){
        return users.containsKey(username);
    }

    public User getUser(String username){
        return users.get(username);
    }

}
