import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImagingOpException;
import java.io.File;

public class ImageResizer implements  Runnable {
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }
    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;

                }
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.SPEED, (newWidth * 2));
                BufferedImage newImage1 = Scalr.resize(newImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, newWidth, newHeight, Scalr.OP_ANTIALIAS);


                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage1, "jpg", newFile);
            }
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
