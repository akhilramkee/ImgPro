/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;

import java.awt.image.BufferedImage;

/**
 *
 * @author sai
 */
public class RleCompressor implements ImageCompressor{

    class Triplet{
        public int count;
        public int element;
        
        Triplet(int count ,int element){
            this.count = count;
            this.element = element;
        } 
    }
    
    private int count = 0;
    
    public int getCount(){
        return count;
    }
    
    public void setCount(int newCount){
        count = newCount;
    }
    
    @Override
    public BufferedImage compressor(BufferedImage image){
        
        int height = image.getHeight();
        int width = image.getWidth();
        
        for(int y=0;y < height;y++){
            for(int x=0; x<width;x++){
                int pixel = image.getRGB(x,y);
                
                int a = (pixel>>24)&0xff;
                int R = (pixel>>16)&0xff;
                int G = (pixel>>8)&0xff;
                int B = pixel&0xff;
                

            }
        }
        
        return image;
    }
    
    @Override
    public synchronized BufferedImage compressor(BufferedImage image,int ThreadAmount){
        
        int width = image.getWidth(); 
        int height = image.getHeight(); 
        int threadCounter = this.getCount();
        this.setCount(threadCounter+1);
  
        int heightPerThread = height/ThreadAmount;
        int modulo = height%ThreadAmount;
        // convert to greyscale 
        for (int y = (heightPerThread*threadCounter); y < (heightPerThread*(threadCounter+1)); y++) 
        { 
            for (int x = 0; x < width; x++) 
            { 
                int pixel = image.getRGB(x,y);
                
                int a = (pixel>>24)&0xff;
                int R = (pixel>>16)&0xff;
                int G = (pixel>>8)&0xff;
                int B = pixel&0xff;
                
            } 
        }
        
        if(modulo > 0 && ThreadAmount == (threadCounter+1)){
            for (int y = (heightPerThread * ThreadAmount); y < (heightPerThread * ThreadAmount) + modulo - 2; y++) {
                for (int x = 0; x < width; x++) {
                    
                int pixel = image.getRGB(x,y);
                
                int a = (pixel>>24)&0xff;
                int R = (pixel>>16)&0xff;
                int G = (pixel>>8)&0xff;
                int B = pixel&0xff;
                
                int newRed = (int)(0.393*R+0.769*G+0.189*B);
                int newGreen = (int)(0.349*R+0.686*G+0.168*B);
                int newBlue = (int)(0.272*R+0.534*G+0.131*B);
                
                if(newRed>255)
                    R=255;
                else
                    R=newRed;
                if(newGreen>255)
                    G = 255;
                else
                    G =newGreen;
                if(newBlue>255)
                    B=255;
                else
                    B=newBlue;
                
                pixel = (a<<24)|(R<<16)|(G<<8)|B;
                
                image.setRGB(x, y, pixel);
                }
            }
        }
        return image;
    }
        
}
