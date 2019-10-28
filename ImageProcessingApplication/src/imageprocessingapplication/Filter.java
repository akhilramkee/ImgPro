/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * 
 * Interface class which the key structure for extending any kind of filters such as Image, Audio and Video.
 * This package just grazes the implementation of a image based filter library
 * 
 * @author Sai
 * @author Akhilesh
 */
interface Filter<T>{

    T filter(T entity);
    
}
