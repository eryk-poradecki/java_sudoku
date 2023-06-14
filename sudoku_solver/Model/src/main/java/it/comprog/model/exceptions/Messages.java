package it.comprog.model.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    public static String getMessage(String messageKey, Locale locale) {
        return ResourceBundle
                .getBundle("it.comprog.model.Messages", Locale.ENGLISH)
                .getString(messageKey);
    }
}
