import noise.ImageWriter;
import noise.SimplexNoise;

import static java.lang.Math.max;

public class Generate {

    public static void main(String args[]){

        int resolution=1024;
        int seed1 = randomMinMax(0,1000);
        int seed2 = randomMinMax(0,1000);

        System.out.println(seed1 + " | " + seed2);

        while(seed1 == seed2) {
            seed2 = (int) Math.random();
        }

        System.out.println(seed1 + " | " + seed2);

        double[][] heightMap = generateheightMap(resolution,11,0.65, seed1);
        double[][] moistureMap = generateheightMap(resolution,11,0.65, seed2);
//        double[][] gradient = generateGradient(resolution,randomMinMax(0 + resolution / 8, resolution - resolution / 8), randomMinMax(0 + resolution / 8, resolution - resolution / 8));
        double[][] gradient = generateGradient(resolution,resolution / 2, resolution / 2);

        double[][] temp = new double[resolution][resolution];
        double[][] temp2 = new double[resolution][resolution];
        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                temp[x][y] = (heightMap[x][y] - gradient[x][y]);
                heightMap[x][y] = temp[x][y];

                temp[x][y] = -temp[x][y];

//                temp2[x][y] = (moistureMap[x][y] - gradient[x][y]);
//                moistureMap[x][y] = -temp[x][y];
            }
        }

        ImageWriter.generateImage(heightMap, moistureMap,true,"saved.png");
        ImageWriter.generateImage(temp,moistureMap,false,"temp.png");
        ImageWriter.generateImage(gradient,moistureMap,false,"gradient.png");
        ImageWriter.generateImage(moistureMap,moistureMap,false,"moisture.png");
    }

    private static double[][] generateGradient(int resolution, int centerX, int centerY) {
        double[][] valueArray = new double[resolution][resolution];

        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {

                double distanceX = (centerX - x) * (centerX - x);
                double distanceY = (centerY - y) * (centerY - y);

                double distanceToCenter = max(distanceX,distanceY);

                distanceToCenter = distanceToCenter / (resolution / 1.75) / (resolution / 2);
//                System.out.println(distanceToCenter);

                valueArray[x][y] = distanceToCenter;


            }
        }

        return valueArray;
    }

    private static double[][] generateheightMap(int resolution, int octaveCount, double persistence, int seed) {
        SimplexNoise simplexNoise = new SimplexNoise(octaveCount,persistence, seed);
        double[][] result = new double[resolution][resolution];

        for(int x=0;x<resolution;x++){
            for(int y=0;y<resolution;y++){
                double e = simplexNoise.getNoise(x,y) / 2 + 0.5;
                result[x][y]= e;
            }
        }

        return result;
    }

    public static int randomMinMax(int Min, int Max) {
        return (int) (Math.random()*(Max-Min + 1))+Min;
    }
}