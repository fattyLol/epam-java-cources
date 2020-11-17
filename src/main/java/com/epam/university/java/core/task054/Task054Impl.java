package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {


    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = originalImage(inputFilePath);
            int height = image.getHeight();
            int width = image.getWidth();

            int minX = image.getMinX();
            int minY = image.getMinY();

            for (int i = minY; i < width; i++) {
                for (int j = minX; j < height; j++) {
                    int pixelRgb = image.getRGB(i, j);

                    int red = getRed(pixelRgb);
                    int green = getGreen(pixelRgb);
                    int blue = getBlue(pixelRgb);

                    int grey = (red + green + blue) / 3;
                    Color color = new Color(grey, grey, grey);
                    image.setRGB(i, j, color.getRGB());
                }
            }

            File outputFile = new File(outputFilePath);
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = originalImage(inputFilePath);
            int height = image.getHeight();
            int width = image.getWidth();
            int minX = image.getMinX();
            int minY = image.getMinY();

            for (int i = minY; i < width; i++) {
                for (int j = minX; j < height; j++) {
                    int pixelRgb = image.getRGB(i, j);

                    int red = getRed(pixelRgb);
                    int green = getGreen(pixelRgb);
                    int blue = getBlue(pixelRgb);

                    int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
                    int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
                    int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

                    if (newRed > 255) {
                        newRed = 255;
                    }
                    if (newGreen > 255) {
                        newGreen = 255;
                    }
                    if (newBlue > 255) {
                        newBlue = 255;
                    }

                    Color color = new Color(newRed, newGreen, newBlue);
                    image.setRGB(i, j, color.getRGB());
                }
            }

            File outputFile = new File(outputFilePath);
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image;
        try {
            image = originalImage(inputFilePath);
            int height = image.getHeight();
            int width = image.getWidth();
            int minX = image.getMinX();
            int minY = image.getMinY();
            BufferedImage newImage = new BufferedImage(width, height, image.getType());


            for (int i = minX; i < width; i++) {
                for (int j = minY; j < height; j++) {
                    int pixelRgb = image.getRGB(i, j);
                    newImage.setRGB(width - i - 1, j, pixelRgb);
                }
            }
            newImage.flush();

            File outputFile = new File(outputFilePath);
            ImageIO.write(newImage, "jpg", outputFile);
            return newImage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public int getRed(int pixel) {
        String hex = Integer.toHexString(pixel);
        String hexRed = String.valueOf(hex.charAt(2)) + hex.charAt(3);
        int red = Integer.valueOf(hexRed, 16);

        return red;
    }

    @Override
    public int getGreen(int pixel) {
        String hex = Integer.toHexString(pixel);
        String hexGreen = String.valueOf(hex.charAt(4)) + hex.charAt(5);
        int green = Integer.valueOf(hexGreen, 16);

        return green;
    }

    @Override
    public int getBlue(int pixel) {
        String hex = Integer.toHexString(pixel);
        String hexBlue = String.valueOf(hex.charAt(6)) + hex.charAt(7);
        int blue = Integer.valueOf(hexBlue, 16);

        return blue;
    }
}
