package ca.rasul.sparkjava.basicAuth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Created by nrasul on 10/13/14.
 */
public class TestBasicAuth {
    @Test
    public void testBasicAuthentication(){
        byte[] encodedBytes = Base64.encodeBase64("Test".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        try {
            URL url = new URL ("http://localhost:4567/login/user/pass");
            byte[] encoding = Base64.encodeBase64("test1:test1".getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   =
                    new BufferedReader (new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
