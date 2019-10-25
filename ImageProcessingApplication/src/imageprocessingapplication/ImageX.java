/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    
    private int count = 0;
    
    public int getCount(){
        return count;
    }
    
    public void setCount(int newCount){
        count = newCount;
    }
    
    public void resetCount(){
        count = 0;
    }
    
    public ImageX(String inputFile){
        this.inputFileLocation = inputFile;
        this.outputFileLocation = "untitled.jpg";
    }
    
    public ImageX(String inputFile,String outputFile){
        this.inputFileLocation = inputFile;
        this.outputFileLocation = outputFile;
    }
    
    public boolean open() throws IOException{
        
        int width = 963;
        int height = 640;
        
        try{
            System.out.println(inputFileLocation);
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
            
            this.resetCount();
           
           }catch(IOException e)
           {
               throw new IOException(e);
               
           }
        }
        return true;
    }
    
    /**
     * Writes the output file onto a specific location
     * @param outFileLocation The Location to store the file
     * @return true/false
     * @throws ImageNotLoadedException
     * @throws IOException 
     */
    public boolean write(String outFileLocation) throws ImageNotLoadedException,IOException{
        
        this.outputFileLocation = outFileLocation;
        
        if(this.image == null){   
         
            throw new ImageNotLoadedException();
            
        
        }else{
          
           try{
            
            File output_file = new File(this.outputFileLocation);
            
            String outputFormat = getOutputFormat(this.outputFileLocation);
               
            ImageIO.write(this.image,outputFormat,output_file);
            
            this.resetCount();
           
           }catch(IOException e)
           {
               throw new IOException(e);
               
           }
        }
        return true;
    }
    
    /**
     * Parses the Location to get the format of file storage
     * @param Location The location
     * @return extension
     */
    
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
    
    /**
     *  Thread unsafe implementation of Sepia filter
     * @return true/false
     */
    public boolean convertToSepia(){
        
        this.image = new SepiaFilter().filter(image);
        
        return this.image != null;
        
    }
    
    /**
     * Thread safe implementation of Sepia filter
     * @param ThreadAmount Number of threads involved
     * @return  true/false
     */
    synchronized public boolean convertToSepia(int ThreadAmount){
        
        int ThreadCount = this.getCount()%ThreadAmount;
        this.setCount(ThreadCount+1);
        
        this.image = new SepiaFilter().filter(image,ThreadCount, ThreadAmount);
        
        return this.image!=null;
        
    }
    
    /**
     * Thread Unsafe implementation of a Negative filter
     * @return true/false
     */
    
    public boolean convertToNegative(){
        this.image = new NegativeFilter().filter(image);
        
        return this.image != null;
    }
    
    /**
     * Thread safe implementation of a Negative filter
     * @param ThreadAmount Total Number of threads involved
     * @return true/false
     */
    
    synchronized public boolean convertToNegative(int ThreadAmount){
        
        int ThreadCount = this.getCount()%ThreadAmount;
        this.setCount(ThreadCount+1);
        
        this.image = new NegativeFilter().filter(image,ThreadCount,ThreadAmount);
        
        return this.image!=null;
        
    }
    
    /**
     * Thread unsafe implementation of a mirror filter 
     * @return  true/false
     */
    
    public boolean convertToMirror(){
        this.image = new MirrorImage().filter(image);
        
        return this.image!=null;
    }
    
    /**
     * Thread safe implementation of a mirror filter
     * @param ThreadAmount Number of threads involved
     * @return true/false
     */
    synchronized public boolean convertToMirror(int ThreadAmount){
        
        int ThreadCount = this.getCount()%ThreadAmount;
        this.setCount(ThreadCount+1);
        
        this.image = new MirrorImage().filter(image,ThreadCount,ThreadAmount);
        
        return this.image!=null;
        
    }
    
    /**
     * Thread unsafe implementation of a Median Filter
     * @return  true/false
     */
    
    public boolean medianFilter(){
        this.image = new MedianFilter().filter(image);
        
        return this.image!=null;
    }
    
    /**
     * Thread safe implementation of a Median Filter
     * @param ThreadAmount Total number of threads involved
     * @return true/false
     */
    synchronized public boolean medianFilter(int ThreadAmount){
        
        int ThreadCount = this.getCount()%ThreadAmount;
        this.setCount(ThreadCount+1);
        
        this.image = new MedianFilter().filter(this.image,ThreadCount,ThreadAmount);
        
        return this.image!=null;
        
    }
    
    /**
     * Basic implementation of non thread based grayscale filter (Non Threadsafe)
     * @return true/false
     */
    
    public boolean grayscaleFilter(){
        this.image = new GrayscaleFilter().filter(image);
        
        return this.image!=null;
    }
    
    /**
     * Thread based implementation of the grayscale filter (Threadsafe implementation)
     * @param ThreadAmount Total number of threads involved
     * @return  true if image has been converted
     */
    
    synchronized public boolean grayscaleFilter(int ThreadAmount){
        
        int ThreadCount = this.getCount()%ThreadAmount;
        this.setCount(ThreadCount+1);
        
        this.image = new GrayscaleFilter().filter(image,ThreadCount,ThreadAmount);
        
        return this.image!=null;
        
    }
    
    /**
     * The RLE Compression technique has been used to achieve compression here
     * @param Location Location to store the compressed file
     * @return true if the file is compressed / false otherwise
     */
        
    public boolean compress(String Location){
        byte[] compressedByte = new RleCompressor().compressor(image);
        File file = new File(Location);
        try{
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(compressedByte);
                System.out.println("Compressed Image has been written into "+Location);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return compressedByte!=null;
    }
    
    /**
     * 
     * Decompression -------needed to fill
     * 
     * @param CompressedLocation Location to get the compressed file from
     * @param decompressedLocation Location to store the decompressed File
     * @return true/false
     * @throws FileNotFoundException Thrown when Compressed file is not found
     * @throws IOException Thrown when there has been an error in stream
     * @throws ImageNotLoadedException Thrown when the image is null when trying to write it back
     */
    
    
    public boolean decompress(String CompressedLocation,String decompressedLocation) throws FileNotFoundException, IOException, ImageNotLoadedException{
        
        byte[] imageData;
        int bytesread;
       try{
            File InputFile = new File(CompressedLocation);

            FileInputStream is = new FileInputStream(InputFile);

            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
           
            while ((bytesread = is.read()) != -1) {
                bytestream.write(bytesread);
            }

            imageData = bytestream.toByteArray();

            this.image = RleCompressor.decompress(imageData);
            
            this.write(decompressedLocation);
            
            bytestream.close();
            
            is.close();
            
       }catch(ImageNotLoadedException a){
           throw a;
           
       }catch(Exception e){
           throw e;
       }
       return true;
    }
}
