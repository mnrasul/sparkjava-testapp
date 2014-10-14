package ca.rasul.sparkjava.testapp;

import ca.rasul.sparkjava.testapp.controllers.HelloController;
import ca.rasul.sparkjava.testapp.controllers.UserController;
import ca.rasul.sparkjava.testapp.filters.AuthenticationFilter;
import ca.rasul.sparkjava.testapp.services.HelloService;
import ca.rasul.sparkjava.testapp.services.UserService;

;import static spark.Spark.before;

/**
 * Created by nrasul on 9/25/14.
 */

//  http://www.mscharhag.com/2014/06/building-simple-restful-api-with-spark.html
//  http://yobriefca.se/blog/2012/03/20/tinkering-with-spark-a-micro-web-framework-for-java/
public class HelloWorld {

    public static void main(String[] args) {
        UserService userService = new UserService();
        HelloService helloService = new HelloService();

        before("/login/:username/:password", new AuthenticationFilter(userService));
//        before(new AuthorizationFilter());


        UserController userController = new UserController(userService);
        HelloController controller = new HelloController(helloService);

//        get("/hello", (request, response) -> {
//            return getMessage();
//        },new HelloMessageTransformer());

//        get("/throwexception", (request, response) -> {
//            throw new NotFoundException();
//        });

//        exception(NotFoundException.class, (e, request, response) -> {
//            response.status(404);
//            response.body("Resource not found");
//        });
    }

//    private static HelloMessage getMessage() {
//        return new HelloMessage("hahahah");
//    }


}