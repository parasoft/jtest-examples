package examples.mock;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Example to show how to stub constructors
 */
public class ConstructorExample {

    public static long getFileLength(String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        return file.length();
    }
}
