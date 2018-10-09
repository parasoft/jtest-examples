package examples.flowanalysis;

import java.io.RandomAccessFile;

public class AlwaysCloseRAFs {

    public String read(String sFileName, String sMode, int offset) {
        String res = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(sFileName, sMode);
            byte[] bytes = new byte[512];
            int read = raf.read(bytes, offset, bytes.length);
            res = new String(bytes);
        } catch (Exception e) {
            System.out.println();
        }
        return res;
    }

    public String readClose(String sFileName, String sMode, int offset) {
        String res = null;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(sFileName, sMode);
            byte[] bytes = new byte[512];
            int read = raf.read(bytes, offset, bytes.length);
            res = new String(bytes);
        } catch (Exception e) {
            System.out.println();
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
            }
        }
        return res;
    }
}
