package examples.flowanalysis.np;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Example class to use various parameters of rule "Avoid NullPointerException".
 */
public class ExtendedNullPointer {

    String sFileName = "";

    Map supportedLocalesMap = new HashMap();

    int getLineLength() {
        String sFirstLine = "First line";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sFileName));
            sFirstLine = reader.readLine();
        } catch (Exception e) {
            System.out.println("Exception occured. " + e.toString());
            System.out.println("Cause message: " + e.getCause().getMessage());
        } finally {
            close(reader);
        }
        return sFirstLine.length();
    }

    String getCurrentCountryName() {
        String sLanguage = System.getProperty("Language");
        return getCountry(sLanguage.toLowerCase(), true);
    }

    String getCountry(String languageId, boolean bDisplayName) {
        Locale locale = (Locale) supportedLocalesMap.get(languageId);
        if (bDisplayName) {
            return locale.getDisplayCountry();
        }
        return locale.getCountry();
    }

    private void close(Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (Exception e) {
        }
    }
}
