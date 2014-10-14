package ca.rasul.sparkjava.testapp.exceptions;

import ca.rasul.sparkjava.testapp.models.StatusMessage;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

/**
 * Created by nrasul on 10/13/14.
 */
public class GenericExceptionHandler implements ExceptionHandler{
    private final int status;
    private final String messageKey;
    private final String params[];

    public GenericExceptionHandler(int status, String messageKey, String ... params) {
        this.status = status;
        this.messageKey = messageKey;
        this.params = params;
    }

    /**
     * Invoked when an exception that is mapped to this handler occurs during routing
     *
     * @param exception The exception that was thrown during routing
     * @param request   The request object providing information about the HTTP request
     * @param response  The response object providing functionality for modifying the response
     */
    @Override
    public void handle(Exception exception, Request request, Response response) {
        response.status(status);
        response.body(new StatusMessage(status,messageKey,params).json());
    }
}
