package ca.rasul.sparkjava.testapp.controllers;

import ca.rasul.sparkjava.testapp.exceptions.GenericExceptionHandler;
import ca.rasul.sparkjava.testapp.models.HelloMessage;
import ca.rasul.sparkjava.testapp.models.ResourceBundleHelper;
import ca.rasul.sparkjava.testapp.services.UserService;
import ca.rasul.sparkjava.testapp.transformers.HelloMessageTransformer;
import org.apache.http.auth.AuthenticationException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.Spark;

/**
 * Created by nrasul on 9/28/14.
 */
public class UserController {
    private final UserService userService;
    private final HelloMessageTransformer transformer = new HelloMessageTransformer();


    public UserController(UserService userService) {
        this.userService = userService;

        Spark.get("/login/:username/:password", new Route() {
            @Override
            public HelloMessage handle(Request request, Response response) {
                String username = (String)request.params(":username");
                String password = (String)request.params(":password");
                if (userService.login(username, password)) {
                    Session session = request.session(true);
                    session.attribute("username", username);
//                    return new StatusMessage(200,)
                    return new HelloMessage(username + " is loggedin "+ request.session().id()+ "["+password+"]");
                }else{
                    return new HelloMessage(ResourceBundleHelper.getMessage("authorization.invalid.usernameorpassword"));
                }
//                String username = request.session().attribute("username");

            }
        }, transformer);

        Spark.get("/logout/:username", new Route() {
            @Override
            public HelloMessage handle(Request request, Response response) {
                String username = request.session().attribute("username");
                userService.logout(username);
                return new HelloMessage(username + " logged out");
            }
        }, transformer);

        Spark.exception(AuthenticationException.class, new GenericExceptionHandler(401,"authorization.invalid.header"));
        Spark.exception(NullPointerException.class, new GenericExceptionHandler(500,"server.error.nullpointer"));
    }


}
