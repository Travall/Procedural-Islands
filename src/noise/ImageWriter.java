package noise;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageWriter {
    private static final Color OCEAN = new Color(9,82,198);
    private static final Color BEACH = new Color(164, 148, 99);
    private static final Color SNOW = new Color(255, 255, 255);
    private static final Color STONE = new Color(160, 162, 143);
    private static final Color GRASSLAND = new Color(90, 127, 50);

    public static void generateImage(double[][] elevation, double[][] moisture, Boolean colour, String filename) {

        BufferedImage image = new BufferedImage(elevation.length, elevation[0].length, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < elevation[0].length; y++) {
            for (int x = 0; x < elevation.length; x++) {
                double e = elevation[x][y];
                double m = moisture[x][y];
                if (e > 1) {
                    e = 1;
                }
                if (e < 0) {
                    e = 0;
                }

                Color col = new Color((float) e, (float) e, (float) e);
                if(colour) {

                    if (e <= 1) { // snow
                        col = SNOW;
                    }

                    if (e <= 0.95) { // snow
                        col = brightness(SNOW, -20);
                    }

                    if (e <= 0.8) { // stone 2
                        col = STONE;
                    }

                    if (e <= 0.7) { // stone
                        col = brightness(STONE, -20);
                    }

                    if (e <= 0.6) {
                        if (m < 0.3) {
                            col = brightness(GRASSLAND, +30);
                        } else if (m > 0.7) {
                            col = brightness(GRASSLAND, -30);
                        } else {
                            col = GRASSLAND;
                        }
                    }

                    if (e <= 0.5) { // ground 1
                        if (m < 0.3) {
                            col = GRASSLAND;
                        } else if (m > 0.7) {
                            col = brightness(GRASSLAND, -50);
                        } else {
                            col = brightness(GRASSLAND, -30);
                        }
                    }

                    if (e <= 0.4) { // sand 2
                        if (m < 0.3) {
                            col = BEACH;
                        } else if (m > 0.7) {
                            col = brightness(BEACH, -50);
                        } else {
                            col = brightness(BEACH, -30);
                        }
                    }

                    if (e <= 0.35) { // sand 1
                        if (m < 0.3) {
                            col = brightness(BEACH, +30);
                        } else if (m > 0.7) {
                            col = brightness(BEACH, -30);
                        } else {
                            col = BEACH;
                        }
                    }

                    if (e <= 0.3) { // sea 2
                        col = OCEAN;
                    }

                    if (e <= 0.2) { // sea 1
                        col = brightness(OCEAN, -20);
                    }
                }
//
//                if(col == SNOW) {
//                    Graphics2D g2d = image.createGraphics();
//                    g2d.setFont(new Font("Serif", Font.BOLD, 56));
//                    g2d.setPaint(Color.red);
//                    g2d.drawString("Gay man",x,y);
//                    g2d.dispose();
//                }

                image.setRGB(x, y, col.getRGB());

//                if(e <= 0.1) { // sea 1
//                    image.setRGB(x, y, OCEAN.getRGB());
//                    continue;
//                }
//                if(e <= 0.25) { // sea 2
//                    image.setRGB(x, y, OCEAN.brighter().getRGB());
//                    continue;
//                }
//                if(e <= 0.3) { // sand 1
//                    image.setRGB(x, y, BEACH.getRGB());
//                    continue;
//                }
//                if(e <= 0.35) { // sand 2
//                    image.setRGB(x, y, BEACH.brighter().getRGB());
//                    continue;
//                }
//
//                if(e <= 0.4) { // ground 1
//                    if(m < 0.3) {
//                        image.setRGB(x, y, GRASSLAND.brighter().getRGB());
//                        continue;
//                    }
//                    if(m > 0.7) {
//                        image.setRGB(x, y, GRASSLAND.darker().getRGB());
//                        continue;
//                    }
//                    image.setRGB(x, y, GRASSLAND.getRGB());
//                    continue;
//                }
//                if(e <= 0.50) { // ground 2
//                    if(m < 0.3) {
//                        image.setRGB(x, y, GRASSLAND.brighter().brighter().getRGB());
//                        continue;
//                    }
//                    if(m > 0.7) {
//                        image.setRGB(x, y, GRASSLAND.darker().brighter().getRGB());
//                        continue;
//                    }
//                    image.setRGB(x, y, GRASSLAND.brighter().getRGB());
//                    continue;
//                }
//                if(e <= 0.6) { // stone
//                    image.setRGB(x, y, STONE.getRGB());
//                    continue;
//                }
//                if(e <= 0.7) { // stone 2
//                    image.setRGB(x, y, STONE.brighter().getRGB());
//                    continue;
//                }
//                if(e <= 0.8) { // snow
////                    if(m <= 0.5) {
////                        image.setRGB(x, y, SNOW.darker().getRGB());
////                        continue;
////                    }
////                    if(m >= 0.7) {
////                        image.setRGB(x, y, SNOW.brighter().getRGB());
////                        continue;
////                    }
//                    image.setRGB(x, y, SNOW.getRGB());
//                    continue;
//                }
//                if(e <= 1) { // snow
////                    if(m <= 0.5) {
////                        image.setRGB(x, y, SNOW.brighter().getRGB());
////                        continue;
////                    }
////                    if(m >= 0.7) {
////                        image.setRGB(x, y, SNOW.brighter().brighter().getRGB());
////                        continue;
////                    }
//                    image.setRGB(x, y, SNOW.brighter().getRGB());
//                    continue;
//                }

            }
        }

        try {
            // retrieve image
            File outputfile = new File(filename);
            outputfile.createNewFile();

            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            //o no! Blank catches are bad
            throw new RuntimeException("I didn't handle this very well");
        }
    }

    public static Color brightness(Color color, float amount) {
        int red = (int) (color.getRed() + amount);
        int green = (int) (color.getGreen() + amount);
        int blue = (int) (color.getBlue() + amount);
        if(red<1) red = 0;
        if(blue<1) blue = 0;
        if(green<1) green = 0;
        return new Color(red, green, blue);
    }

}