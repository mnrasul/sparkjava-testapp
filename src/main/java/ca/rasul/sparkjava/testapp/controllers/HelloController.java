package ca.rasul.sparkjava.testapp.controllers;

import java.util.HashMap;
import java.util.Map;

import ca.rasul.sparkjava.testapp.services.HelloService;
import ca.rasul.sparkjava.testapp.transformers.HelloMessageTransformer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Created by nrasul on 9/27/14.
 */
public class HelloController {

    public HelloController(final HelloService helloService){
        MustacheTemplateEngine mte = new MustacheTemplateEngine();

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

        Spark.get("/baba",new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map map = new HashMap();
                map.put("name", "lala");
                return new ModelAndView(map,"baba.mustache");

            }
        },mte);
        Spark.get("/baba2",new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map map = new HashMap();
                map.put("name", "lala2");
                return new ModelAndView(map,"baba.mustache");
            }
        },mte);
    }
}
