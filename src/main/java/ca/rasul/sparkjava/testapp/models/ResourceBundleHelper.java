package ca.rasul.sparkjava.testapp.models;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by nrasul on 10/13/14.
 */
public class ResourceBundleHelper {
    public static String getMessage(String key, Object ... params){
        return MessageFormat.format( ResourceBundle.getBundle("i18n.messages").getString(key),params);
    }
}
