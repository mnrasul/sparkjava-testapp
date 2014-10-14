package ca.rasul.sparkjava.testapp.models;

import javax.security.auth.AuthPermission;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nrasul on 9/28/14.
 */
public class User {

    private final String username;
    private final String password;
    private final List<Permission> permissionList = new ArrayList<>();
    private final String sessionId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.sessionId = null;
    }

    public User(String username, String password, String sessionId) {
        this.username = username;
        this.password = password;
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addPermission(String permission){
        this.permissionList.add(new AuthPermission(permission));
    }

    /**
     * Permission support wild cards. For instance, if you want give someone
     * permission for Hello.* meaning they can execute any Hello Action,
     * then all actions beginning with Hello. will succeed.
     * Hello.add
     * Hello.remove and so on.
     *
     * If somone only has Hello.add permission, and you check for Hello.remove, the
     * hasPermission will fail.
     * @param perm
     * @return
     */
    public boolean hasPermission(String perm){
        final Permission permission = new AuthPermission(perm);
        for (Permission p: permissionList){
            if (p.implies(permission))
                return true;
        }
        //no permission was a match, so return false;
        return false;
    }
}
