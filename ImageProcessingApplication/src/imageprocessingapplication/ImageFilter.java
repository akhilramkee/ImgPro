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
interface ImageFilter extends Filter<BufferedImage>{
    
    @Override
    public BufferedImage filter(BufferedImage img);
    
    public BufferedImage filter(BufferedImage img,int threadPosition,int totalThreads);
    
}
