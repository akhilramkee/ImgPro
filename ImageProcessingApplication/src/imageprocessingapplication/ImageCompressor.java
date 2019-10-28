/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.awt.image.BufferedImage;

/**
 * 
 * Interface extending from compressor. Contains declaration to image based compression techniques.
 * 
 * 
 * @author Sai
 * @author Akhilesh
 */
interface ImageCompressor extends Compressor<byte[],BufferedImage>{
  
    @Override
    public byte[] compress(BufferedImage img);

    
}
