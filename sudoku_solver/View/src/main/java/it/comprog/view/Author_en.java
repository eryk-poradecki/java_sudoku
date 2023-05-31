package it.comprog.view;

import java.util.ListResourceBundle;

public class Author_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"Author"},
                {"Eryk Poradecki"}
        };
    }

    public String toString() {
        String outString = new String();

        Object[][] temp = getContents();

        outString += (String) temp[0][0];
        outString += ": ";
        outString += (String) temp[1][0];

        return outString;
    }
}
