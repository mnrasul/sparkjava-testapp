package ca.rasul.sparkjava.testapp.filters;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.ResourceBundle;

import static spark.Spark.halt;

/**
 * Created by nrasul on 9/27/14.
 */
public class AuthorizationFilter implements Filter {
    @Override
    public void handle(Request request, Response response) throws Exception {
        Session session = request.session();
        String username = session.attribute("username");
        if (username == null || username.isEmpty()){
            String acceptedLanguage = request.headers("Accept-Language");
            halt(401, ResourceBundle.getBundle("i18n.messages").getString("authorization.invalid.session"));
        }
    }
}
