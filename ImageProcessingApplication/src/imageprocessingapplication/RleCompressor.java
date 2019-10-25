/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author sai
 */
public class RleCompressor implements ImageCompressor{

    class Triplet implements Serializable{
        
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
    public byte[] compressor(BufferedImage image){
        
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte = null;
        try{
            ImageIO.write(image, "BMP", baos);
            imageInByte = baos.toByteArray();
            File file = new File("E:/before.txt");
            try{
                try (OutputStream os = new FileOutputStream(file)) {
                    os.write(baos.toByteArray());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
            baos.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
        ByteArrayOutputStream dest = new ByteArrayOutputStream();  
        byte lastByte = imageInByte[0];
        int matchCount = 1;
        for(int i=1; i < imageInByte.length; i++){
             byte thisByte = imageInByte[i];
                if (lastByte == thisByte) {
                    matchCount++;
                }
                else {
                    dest.write((byte)matchCount);  
                    dest.write((byte)lastByte);
                    matchCount=1;
                    lastByte = thisByte;
                }                
        }
        dest.write((byte)matchCount);  
        dest.write((byte)lastByte);
        byte[] runLengthEncode = dest.toByteArray(); 
//        System.out.println(Arrays.toString(runLengthEncode));
        
        return runLengthEncode;
    }
    
    public static BufferedImage decompress(byte[] bytes) {
        BufferedImage image = null;
        ByteArrayOutputStream dest = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; i += 2) {
            byte tmp = bytes[i];
            while (tmp > 0) {
                dest.write(bytes[i + 1]);
                tmp--;
            }
        }
  
        ByteArrayInputStream bais = new ByteArrayInputStream(dest.toByteArray());
        try{
            image = ImageIO.read(bais);
        }catch(Exception e){
            System.out.println(e);
        }
        return image;
    }
    
        
}
