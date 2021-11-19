package ru.flower.shop.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ImageUtils {

    private static final long MAX_FILE_UPLOAD_SIZE = 307200;

    private ImageUtils() {
    }

    public static byte[] photoConvert(MultipartFile avatar) throws IOException {
        String avatarType = getImageType(avatar.getContentType());
        if (avatar.getSize() > MAX_FILE_UPLOAD_SIZE) {
            return null;
        }
        if (avatarType == null) {
            return null;
        } else {
            return toByteArrayAutoClosable(cropImageSquare(avatar.getBytes()), avatarType);
        }
    }

    private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, type.substring(6), out);
        return out.toByteArray();
    }

    private static BufferedImage cropImageSquare(byte[] image) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);

        int height = originalImage.getHeight();
        int width = originalImage.getWidth();

        if (height == width) {
            return originalImage;
        }

        int squareSize = (Math.min(height, width));

        int xc = width / 2;
        int yc = height / 2;

        return originalImage.getSubimage(
                xc - (squareSize / 2),
                yc - (squareSize / 2),
                squareSize,
                squareSize
        );
    }

    private static String getImageType(String contentType) {
        if (contentType == null)
            return null;
        if (!contentType.toLowerCase().startsWith("image/")) {
            return null;
        } else return contentType;
    }

    private static BufferedImage getBufferedImage(byte[] image) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        return ImageIO.read(in);
    }

    public static byte[] getImage(MultipartFile image) throws IOException {
        String imageType = getImageType(image.getContentType());
        if (image.getSize() > MAX_FILE_UPLOAD_SIZE) {
            return null;
        }
        if (imageType == null) {
            return null;
        } else {
            return toByteArrayAutoClosable(getBufferedImage(image.getBytes()), imageType);
        }
    }
}
