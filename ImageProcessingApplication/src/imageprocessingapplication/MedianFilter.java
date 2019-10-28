/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

/**
 * 
 * Median Filter algorithm
 * 
 * @author Sai
 * @author Akhilesh
 */
public class MedianFilter implements ImageFilter{
    
    @Override
    public BufferedImage filter(BufferedImage image){
        
        int width = image.getWidth();
        int height = image.getHeight();
        Color[] surroundedPixel = new Color[9];
        int[] R = new int[9];
        int[] B = new int[9];
        int[] G = new int[9];
        
        for(int j=1;j<height-1;j++){
            for(int i=1;i<width-1;i++){
                surroundedPixel[0] = new Color(image.getRGB(i - 1, j - 1));
                surroundedPixel[1] = new Color(image.getRGB(i - 1, j));
                surroundedPixel[2] = new Color(image.getRGB(i - 1, j + 1));
                surroundedPixel[3] = new Color(image.getRGB(i, j + 1));
                surroundedPixel[4] = new Color(image.getRGB(i + 1, j + 1));
                surroundedPixel[5] = new Color(image.getRGB(i + 1, j));
                surroundedPixel[6] = new Color(image.getRGB(i + 1, j - 1));
                surroundedPixel[7] = new Color(image.getRGB(i, j - 1));
                surroundedPixel[8] = new Color(image.getRGB(i, j));
                for (int k = 0; k < 9; k++) {
                    R[k] = surroundedPixel[k].getRed();
                    B[k] = surroundedPixel[k].getBlue();
                    G[k] = surroundedPixel[k].getGreen();
                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                image.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());;
            }
        }
        return image;
    }
    
    @Override
    public synchronized BufferedImage filter(BufferedImage bufferedImage,int threadCounter,int threadAmount){
        
        int imageHeight = bufferedImage.getHeight();
        int imageWidth = bufferedImage.getWidth();

        int heightPerThread = imageHeight / threadAmount;
        int modulo = imageHeight % threadAmount;
        Color[] surroundedPixel = new Color[9];
        int[] R = new int[9];
        int[] B = new int[9];
        int[] G = new int[9];

        for (int j = 1 + ((heightPerThread - 2) * threadCounter); j < (heightPerThread * (threadCounter + 1)) - 1; j++) {
            for (int i = 1; i < bufferedImage.getWidth() - 1; i++) {
                surroundedPixel[0] = new Color(bufferedImage.getRGB(i - 1, j - 1));
                surroundedPixel[1] = new Color(bufferedImage.getRGB(i - 1, j));
                surroundedPixel[2] = new Color(bufferedImage.getRGB(i - 1, j + 1));
                surroundedPixel[3] = new Color(bufferedImage.getRGB(i, j + 1));
                surroundedPixel[4] = new Color(bufferedImage.getRGB(i + 1, j + 1));
                surroundedPixel[5] = new Color(bufferedImage.getRGB(i + 1, j));
                surroundedPixel[6] = new Color(bufferedImage.getRGB(i + 1, j - 1));
                surroundedPixel[7] = new Color(bufferedImage.getRGB(i, j - 1));
                surroundedPixel[8] = new Color(bufferedImage.getRGB(i, j));
                for (int k = 0; k < 9; k++) {
                    R[k] = surroundedPixel[k].getRed();
                    B[k] = surroundedPixel[k].getBlue();
                    G[k] = surroundedPixel[k].getGreen();
                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                bufferedImage.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());
            }
        }
        if (modulo > 0 && threadAmount == (threadCounter + 1)) {
            for (int j = (heightPerThread * threadAmount); j < (heightPerThread * threadAmount) + modulo - 2; j++) {
                for (int i = 1; i < bufferedImage.getWidth() - 1; i++) {
                    surroundedPixel[0] = new Color(bufferedImage.getRGB(i - 1, j - 1));
                    surroundedPixel[1] = new Color(bufferedImage.getRGB(i - 1, j));
                    surroundedPixel[2] = new Color(bufferedImage.getRGB(i - 1, j + 1));
                    surroundedPixel[3] = new Color(bufferedImage.getRGB(i, j + 1));
                    surroundedPixel[4] = new Color(bufferedImage.getRGB(i + 1, j + 1));
                    surroundedPixel[5] = new Color(bufferedImage.getRGB(i + 1, j));
                    surroundedPixel[6] = new Color(bufferedImage.getRGB(i + 1, j - 1));
                    surroundedPixel[7] = new Color(bufferedImage.getRGB(i, j - 1));
                    surroundedPixel[8] = new Color(bufferedImage.getRGB(i, j));
                    for (int k = 0; k < 9; k++) {
                        R[k] = surroundedPixel[k].getRed();
                        B[k] = surroundedPixel[k].getBlue();
                        G[k] = surroundedPixel[k].getGreen();
                    }
                    Arrays.sort(R);
                    Arrays.sort(G);
                    Arrays.sort(B);
                    bufferedImage.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());
                }
            }
        }
        return bufferedImage;
    }
    
}
