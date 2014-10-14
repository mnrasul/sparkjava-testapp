package ca.rasul.sparkjava.testapp.controllers;

import ca.rasul.sparkjava.testapp.services.HelloService;
import ca.rasul.sparkjava.testapp.transformers.HelloMessageTransformer;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by nrasul on 9/27/14.
 */
public class HelloController {

    public HelloController(final HelloService helloService){

        Spark.get("/hello",new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return helloService.sayHello();
            }
        }, new HelloMessageTransformer());

        Spark.get("/hello/:greeting",new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return helloService.sayGreeting(request.params(":greeting"));
            }
        }, new HelloMessageTransformer());
    }
}
