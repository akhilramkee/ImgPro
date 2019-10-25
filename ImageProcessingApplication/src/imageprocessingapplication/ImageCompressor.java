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
interface ImageCompressor extends Compressor<BufferedImage>{
    
    @Override
    public BufferedImage compressor(BufferedImage img);
    
    public BufferedImage compressor(BufferedImage img,int threadPosition);
    
}
