//package noise;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class ImageWriter3 {
//    private static final Color OCEAN = new Color(7,66,158);
//    private static final Color BEACH = new Color(164, 148, 99);
//    private static final Color SCORCHED = new Color(161, 157, 164);
//    private static final Color BARE = new Color(164, 152, 126);
//    private static final Color TUNDRA = new Color(31, 164, 145);
//    private static final Color SNOW = new Color(245, 245, 245);
//    private static final Color TEMPERATE_DESERT = new Color(155, 164, 107);
//    private static final Color GRASSLAND = new Color(115, 164, 54);
//    private static final Color TEMPERATE_DECIDUOUS_FOREST = new Color(35, 130, 65);
//    private static final Color TEMPERATE_RAIN_FOREST = new Color(33, 170, 83);
//    private static final Color SUBTROPICAL_DESERT = new Color(136, 245, 174);
//    private static final Color TROPICAL_SEASONAL_FOREST = new Color(187, 119, 36);
//    private static final Color TROPICAL_RAIN_FOREST = new Color(187, 0, 63);
//    private static final Color SHRUBLAND = new Color(138, 100, 0);
//    private static final Color TAIGA = new Color(160, 162, 143);
//    //just convinence methods for debug
//
//    public static void generateImage(double[][] elevation, double[][] moisture, String filename){
//        //this takes and array of doubles between 0 and 1 and generates a grey scale image from them
//
//        BufferedImage image = new BufferedImage(elevation.length,elevation[0].length, BufferedImage.TYPE_INT_RGB);
//
//        for (int y = 0; y < elevation[0].length; y++)
//        {
//            for (int x = 0; x < elevation.length; x++)
//            {
//                double e = elevation[x][y];
//                double m = moisture[x][y];
//                if (e>1){
//                    e=1;
//                }
//                if (e<0){
//                    e=0;
//                }
//
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
////                if (e > 0.8) {
////                    if (m < 0.1) {
////                        image.setRGB(x, y, SCORCHED.getRGB());
////                        continue;
////                    }
////                    if (m < 0.2) {
////                        image.setRGB(x, y, BARE.getRGB());
////                        continue;
////                    }
////                    if (m < 0.5) {
////                        image.setRGB(x, y, TUNDRA.getRGB());
////                        continue;
////                    }
////                    image.setRGB(x, y, SNOW.getRGB());
////                    continue;
////                }
////
////                if (e > 0.6) {
////                    if (m < 0.33) {
////                        image.setRGB(x, y, TEMPERATE_DESERT.getRGB());
////                        continue;
////                    }
////                    if (m < 0.66) {
////                        image.setRGB(x, y, SHRUBLAND.getRGB());
////                        continue;
////                    }
////                    image.setRGB(x, y, TAIGA.getRGB());
////                    continue;
////                }
////
////                if (e > 0.3) {
////                    if (m < 0.16) {
////                        image.setRGB(x, y, TEMPERATE_DESERT.getRGB());
////                        continue;
////                    }
////                    if (m < 0.50) {
////                        image.setRGB(x, y, GRASSLAND.getRGB());
////                        continue;
////                    }
////                    if (m < 0.83) {
////                        image.setRGB(x, y, TEMPERATE_DECIDUOUS_FOREST.getRGB());
////                        continue;
////                    }
////                    image.setRGB(x, y, TEMPERATE_RAIN_FOREST.getRGB());
////                    continue;
////                }
////
////                if (m < 0.16) {
////                    image.setRGB(x, y, SUBTROPICAL_DESERT.getRGB());
////                    continue;
////                }
////                if (m < 0.33) {
////                    image.setRGB(x, y, GRASSLAND.getRGB());
////                    continue;
////                }
////                if (m < 0.66) {
////                    image.setRGB(x, y, TROPICAL_SEASONAL_FOREST.getRGB());
////                    continue;
////                }
////                image.setRGB(x, y, TROPICAL_RAIN_FOREST.getRGB());
////                continue;
//
//                if(elevation[x][y] <= 0.4) { // ground 1
//                    if(moisture[x][y] <= 0.5) {
//                        image.setRGB(x, y, new Color(65, 94, 34).getRGB());
//                        continue;
//                    }
//                   if(moisture[x][y] >= 0.7) {
//                        image.setRGB(x, y, new Color(89, 132, 53).getRGB());
//                        continue;
//                    }
//                    image.setRGB(x, y, new Color(76, 108, 36).getRGB());
//                    continue;
//                }
//                if(elevation[x][y] <= 0.50) { // ground 2
//                    if(moisture[x][y] <= 0.5) {
//                        image.setRGB(x, y, new Color(82, 116, 57).getRGB());
//                        continue;
//                    }
//                    if(moisture[x][y] >= 0.6) {
//                        image.setRGB(x, y, new Color(109, 142, 58).getRGB());
//                        continue;
//                    }
//                    image.setRGB(x, y, new Color(88, 118, 52).getRGB());
//                    continue;
//                }
//                if(elevation[x][y] <= 0.6) { // stone
//                    image.setRGB(x, y, STONE.);
//                    continue;
//                }
//                if(elevation[x][y] <= 0.7) { // stone 2
//                    image.setRGB(x, y, new Color(160, 162, 143).getRGB());
//                    continue;
//                }
//                if(elevation[x][y] <= 0.8) { // snow
//                    if(moisture[x][y] > 0.5) {
//                        image.setRGB(x, y, SNOW.getRGB());
//                        continue;
//                    } else {
//                        image.setRGB(x, y, STONE.getRGB());
//                    }
//                    continue;
//                }
//                if(elevation[x][y] <= 1) { // snow
////                    if(moisture[x][y] <= 0.5) {
////                        image.setRGB(x, y, new Color(213, 220, 227).getRGB());
////                        continue;
////                    }
//                    image.setRGB(x, y, new Color(237,245,252).getRGB());
//                    continue;
//                }
//
//                System.out.println(elevation[x][y]);
//            }
//        }
//
//        try {
//            // retrieve image
//            File outputfile = new File(filename);
//            outputfile.createNewFile();
//
//            ImageIO.write(image, "png", outputfile);
//        } catch (IOException e) {
//            //o no! Blank catches are bad
//            throw new RuntimeException("I didn't handle this very well");
//        }
//    }
//
//
//
//}