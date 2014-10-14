package ca.rasul.sparkjava.testapp.filters;


import ca.rasul.sparkjava.testapp.UserSessionIdCache;
import ca.rasul.sparkjava.testapp.services.UserService;
import org.apache.commons.codec.binary.Base64;
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
    public void handle(Request request, Response response) throws AuthenticationException {

        if (!request.headers().contains(AUTHORIZATION)){
            throw new AuthenticationException();
        }
        final String authorizationHeader = request.headers(AUTHORIZATION);
        byte[] pass = Base64.decodeBase64(authorizationHeader);
        String x = new String(pass);
        System.out.println(x);
        String username = (String)request.params(":username");
        String password = (String)request.params(":password");
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
    }
}
