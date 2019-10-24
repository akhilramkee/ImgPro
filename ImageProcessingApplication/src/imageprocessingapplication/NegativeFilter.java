/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 *
 * @author Akhilesh
 */
public class NegativeFilter implements Filter<BufferedImage>{
    
    @Override
    public BufferedImage convert(BufferedImage image){
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                int p = image.getRGB(x,y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                
                r = 255-r;
                g = 255-g;
                b = 255-b;
                
                p = (a<<24)|(r<<16)|(g<<8)|b;
                image.setRGB(x, y, p);
            }
        }
        
        return image;
    }
    
}
