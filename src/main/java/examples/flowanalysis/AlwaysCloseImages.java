package examples.flowanalysis;

import java.awt.Image;

import javax.imageio.ImageReader;

import javax.imageio.spi.ImageReaderSpi;

public class AlwaysCloseImages {

    public Image readImage(ImageReaderSpi spi) {
        Image image = null;
        try {
            ImageReader imgReader = spi.createReaderInstance();
            image = imgReader.read(0);
        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        }
        return image;
    }

    public Image readImageClose(ImageReaderSpi spi) {
        Image image = null;
        ImageReader imgReader = null;
        try {
            imgReader = spi.createReaderInstance();
            image = imgReader.read(0);
        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        } finally {
            imgReader.dispose();
        }
        return image;
    }
}
