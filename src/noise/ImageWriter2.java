package noise;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWriter2 {
    //just convinence methods for debug

    public static void greyWriteImage(double[][] data, String filename){
        //this takes and array of doubles between 0 and 1 and generates a grey scale image from them

        BufferedImage image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < data[0].length; y++)
        {
            for (int x = 0; x < data.length; x++)
            {
                if (data[x][y]>1){
                    data[x][y]=1;
                }
                if (data[x][y]<0){
                    data[x][y]=0;
                }

                double SEA_LEVEL = 1;

                Color col = new Color((float)data[x][y],(float)data[x][y],(float)data[x][y]);
                if(data[x][y] <= 0.35) { // sea 1
                    col = new Color(7,66,158);
                } else if(data[x][y] <= 0.45) { // sea 2
                    col = new Color(9,82,198);
                } else if(data[x][y] <= 0.45) { // sand 1
                    col = new Color(164, 148, 99);
                } else if(data[x][y] <= 0.5) { // sand 2
                    col = new Color(194,178,128);
                } else if(data[x][y] <= 0.6) { // ground 1
                    col = new Color(76, 108, 36);
                } else if(data[x][y] <= 0.70) { // ground 2
                    col = new Color(96, 128, 56);
                } else if(data[x][y] <= 0.8) { // stone
                    col = new Color(139, 141, 122);
                } else if(data[x][y] <= 0.9) { // stone
                    col = new Color(160, 162, 143);
                } else if(data[x][y] <= 0.95) { // snow
                    col = new Color(208, 216, 223);
                } else if(data[x][y] <= 1) { // snow
                    col = new Color(237,245,252);
                }

                image.setRGB(x, y, col.getRGB());
//                System.out.println(data[x][y]);
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



}