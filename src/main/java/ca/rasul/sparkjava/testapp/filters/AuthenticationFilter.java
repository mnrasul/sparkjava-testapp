package ca.rasul.sparkjava.testapp.filters;


import ca.rasul.sparkjava.testapp.UserSessionIdCache;
import ca.rasul.sparkjava.testapp.services.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.ProtocolException;
import org.apache.http.auth.AuthenticationException;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Session;

import static spark.Spark.halt;

/**
 * Created by nrasul on 9/27/14.
 */
public class AuthenticationFilter implements Filter {
    final UserService userService;
    private final String AUTHORIZATION = "Authorization";

    public AuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(Request request, Response response) throws ProtocolException {

        if (!request.headers().contains(AUTHORIZATION)){
            throw new AuthenticationException();
        }
        final String authorizationHeader = request.headers(AUTHORIZATION);
        if (authorizationHeader.indexOf("Basic ")< 0){
            throw new ProtocolException();
        }
        //first index will contain an empty string, second contains username:password
        String credentials[] = new String(Base64.decodeBase64(authorizationHeader.split("Basic ")[1])).split(":");
        String username = credentials[0];
        String password = credentials[1];
        request.session().attribute("username",username);
        request.session().attribute("username",password);

        if (userService.login(username, password)) {
            String sessionId = UserSessionIdCache.getInstance().get(username);
            Session session = null;
            if (sessionId == null || sessionId.isEmpty()){
                session = request.session(true);
            }else{
                session = request.session();
            }

            UserSessionIdCache.getInstance().put(username, session.id());
        }else{
            halt(401);
        }

        System.out.println(String.format("username:%s - %s",username,password));

    }
}
