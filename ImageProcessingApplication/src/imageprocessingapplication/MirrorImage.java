/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.awt.image.BufferedImage;
/**
 *
 * @author Akhilesh
 */
public class MirrorImage implements ImageFilter{
    
    
    private int count = 0;
    
    public int getCount(){
        return count;
    }
    
    public void setCount(int newCount){
        count = newCount;
    }
    
    
    public BufferedImage filter(BufferedImage image){
        int width = image.getWidth(); 
        int height = image.getHeight(); 
  
        // BufferedImage for mirror image 
        BufferedImage mimg = new BufferedImage(width, height, 
                                        BufferedImage.TYPE_INT_ARGB); 
  
        // Create mirror image pixel by pixel 
        for (int y = 0; y < height; y++) 
        { 
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) 
            { 
                // lx starts from the left side of the image 
                // rx starts from the right side of the image 
                // lx is used since we are getting pixel from left side 
                // rx is used to set from right side 
                // get source pixel value 
                int p = image.getRGB(lx, y); 
  
                // set mirror image pixel value 
                mimg.setRGB(rx, y, p); 
            } 
        } 
        return mimg;
    }
    
    public BufferedImage filter(BufferedImage image,int threadCounter,int ThreadAmount){
        
        this.setCount(threadCounter+1);
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        int heightPerThread = height/ThreadAmount;
        int modulo = height%ThreadAmount;
        
        for(int y=(heightPerThread*threadCounter);y<(heightPerThread*(threadCounter+1));y++){
            for(int lx=0,rx=width-1;lx<width;lx++,rx--){
                int p = image.getRGB(lx, y);
                image.setRGB(rx,y,p);
            }
        }
        return image;
    }
    
    
    
}
