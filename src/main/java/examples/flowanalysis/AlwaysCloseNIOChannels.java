package examples.flowanalysis;

import java.io.FileInputStream;

import java.nio.ByteBuffer;

import java.nio.channels.FileChannel;

public class AlwaysCloseNIOChannels {

    public void process(String filename) {
        try {
            FileInputStream fInput = new FileInputStream(filename);
            FileChannel channel = fInput.getChannel();
            ByteBuffer dst = ByteBuffer.allocate(512);
            int read = channel.read(dst);
            // ...
        } catch (Exception ioe) {
            System.out.println("Exception occured: " + ioe);
        }
    }

    public void processClose(String filename) {
        FileChannel channel = null;
        try {
            FileInputStream fInput = new FileInputStream(filename);
            channel = fInput.getChannel();
            ByteBuffer dst = ByteBuffer.allocate(512);
            int read = channel.read(dst);
            // ...
        } catch (Exception ioe) {
            System.out.println("Exception occured: " + ioe);
        } finally {
            try {
                channel.close();
            } catch (Exception e) {
            }
        }
    }
}
