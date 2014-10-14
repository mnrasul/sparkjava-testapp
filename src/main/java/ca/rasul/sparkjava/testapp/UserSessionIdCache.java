package ca.rasul.sparkjava.testapp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nrasul on 9/28/14.
 */
public class UserSessionIdCache {
    private static final ConcurrentHashMap<String, String> userCache = new ConcurrentHashMap<>();
    private static final UserSessionIdCache instance = new UserSessionIdCache();

    public boolean has(String username){
        return userCache.containsKey(username);
    }

    public void put(String username, String sessionId){
        userCache.putIfAbsent(username, sessionId);
    }

    public String get(String username){
        return userCache.get(username);
    }

    public static UserSessionIdCache getInstance(){
        return instance;
    }
}
