package ca.rasul.sparkjava.testapp.services;

import ca.rasul.sparkjava.testapp.models.HelloMessage;

/**
 * Interact with persistent store
 * Created by nrasul on 9/27/14.
 */
public class HelloService {

    public HelloMessage sayHello(){
        return new HelloMessage("hello");
    }
    public HelloMessage sayGreeting(String greeting){
        return new HelloMessage(greeting);
    }
}
