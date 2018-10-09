package examples.flowanalysis;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.InputStream;
import java.io.OutputStream;

import java.util.Vector;

public class AlwaysCloseXMLEncDec {

    public void store(OutputStream out, Object[] objects) {
        try {
            XMLEncoder encoder = new XMLEncoder(out);
            for (int i = 0; i < objects.length; i++) {
                encoder.writeObject(objects[i]);
            }
        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        }
    }

    public Object[] read(InputStream in) {
        Vector vObjs = new Vector();
        try {
            XMLDecoder decoder = new XMLDecoder(in);
            Object obj = decoder.readObject();
            while (obj != null) {
                vObjs.add(obj);
                obj = decoder.readObject();
            }
        } catch (ArrayIndexOutOfBoundsException endException) {
            // do nothing, indicates that there are no more objects in the
            // stream
        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        }
        return vObjs.toArray();
    }

    public void storeClose(OutputStream out, Object[] objects) {
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(out);
            for (int i = 0; i < objects.length; i++) {
                encoder.writeObject(objects[i]);
            }
        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        } finally {
            encoder.close();
        }
    }

    public Object[] readClose(InputStream in) {
        Vector vObjs = new Vector();
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(in);
            Object obj = decoder.readObject();
            while (obj != null) {
                vObjs.add(obj);
                obj = decoder.readObject();
            }
        } catch (ArrayIndexOutOfBoundsException endException) {
            // do nothing, indicates that no more objects in the stream
        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        } finally {
            decoder.close();
        }
        return vObjs.toArray();
    }
}
