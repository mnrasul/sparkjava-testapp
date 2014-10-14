package ca.rasul.sparkjava.testapp.transformers;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by nrasul on 9/27/14.
 */
public class HelloMessageTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
