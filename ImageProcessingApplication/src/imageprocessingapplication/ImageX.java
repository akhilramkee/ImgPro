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


class ImageNotLoadedException extends Exception{
 
    public ImageNotLoadedException(){
        super("Image should be Loaded before written back");
    }
    
}



public class ImageX {
    
    String inputFileLocation,outputFileLocation;
    BufferedImage image = null;
    
    public ImageX(String inputFile){
        this.inputFileLocation = inputFile;
        this.outputFileLocation = "E:\\untitled.jpg";
    }
    
    public ImageX(String inputFile,String outputFile){
        this.inputFileLocation = inputFile;
        this.outputFileLocation = outputFile;
    }
    
    public boolean open() throws IOException{
        
        int width = 963;
        int height = 640;
        
        try{
            File inputFile = new File(inputFileLocation);
            
            this.image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            
            image = ImageIO.read(inputFile);
            
        }catch(IOException e){
            
            throw e;
        }
        
        return true;
    }
    
    public boolean write() throws ImageNotLoadedException,IOException{
        
        if(this.image == null){   
         
            throw new ImageNotLoadedException();
            
        
        }else{
          
           try{
            
            File output_file = new File(this.outputFileLocation);
            
            String outputFormat = getOutputFormat(this.outputFileLocation);
               
            ImageIO.write(this.image,outputFormat,output_file);
           
           }catch(IOException e)
           {
               throw new IOException(e);
               
           }
        }
        return true;
    }
    
    public String getOutputFormat(String Location)
    {
        String extension = "";
        
        int i = Location.lastIndexOf('.');
        int p = Math.max(Location.lastIndexOf('/'),Location.lastIndexOf('\\'));
        
        if (i>p){
            extension = Location.substring(i+1);
        }
        return extension;
    }
    
    public boolean convertToSepia(){
        
        this.image = new SepiaConverter().convert(image);
        
        if(this.image != null)
            return true;
        else
            return false;
        
    }
    
        
}
