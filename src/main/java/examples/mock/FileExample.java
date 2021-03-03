package examples.mock;

import java.io.File;
import java.io.IOException;

/**
 * Used to show how to define user defined stubs for File objects.
 * 
 * @see examples.mock.FileExampleTest
 */
public class FileExample {

    public static String analyze(File file) {
        StringBuffer tmp = new StringBuffer();
        tmp.append(file.getAbsolutePath());
        tmp.append(':');
        tmp.append(file.setLastModified(100L));
        tmp.append(':');
        tmp.append(file.compareTo(new File("X001.txt")));
        tmp.append(':');
        tmp.append(file.compareTo(new File("XXX")));
        tmp.append(':');
        return tmp.toString();
    }
    
    public static boolean isOversize(String path, long limit)
    {
        try {
            return limit - ConstructorExample.getFileLength(path) > 0;
        } catch (IOException e) {
            System.out.println("an I/O error occurs");
            return false;
        }
    }
}
