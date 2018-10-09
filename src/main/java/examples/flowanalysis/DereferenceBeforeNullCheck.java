package examples.flowanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Class to demonstrate application of BD.PB.DEREF rule. In order to get the
 * violation in 'closeReader' method reported, 'Do not report violation when
 * origin cannot be show' check box in Flow Analysis options pane must be
 * unchecked since the violation has several origins.
 */
abstract class DereferenceBeforeNullCheck {

    abstract BufferedReader getFileSystemReaderForResource(String fileName);

    String readFirstLine(String sFileName) {
        BufferedReader reader = null;
        String sFirstLine = null;
        try {
            reader = new BufferedReader(new FileReader(sFileName));
            sFirstLine = reader.readLine(); // VIOLATION ORIGIN-1
        } catch (FileNotFoundException e) {
            // to obtain an alternative reader we are trying to use factory
            // method
            // we do not have source code for and which in fact may return null
            reader = getFileSystemReaderForResource(sFileName);
            try {
                sFirstLine = reader.readLine(); // VIOLATION ORIGIN-2
            } catch (IOException ioe) {
                // exception handling
            }
        } catch (IOException e) {
            // exception handling
        } finally {
            closeReader(reader);
        }
        return sFirstLine;
    }

    private static void closeReader(Reader reader) {
        if (reader == null) { // VIOLATION (reported only if 'Do not report
                              // violation when origin cannot be
                              // show' check box is unchecked)
            return;
        }
        try {
            reader.close();
        } catch (Exception e) {
            // exception handling
        }
    }
}
