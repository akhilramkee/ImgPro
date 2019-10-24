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
 * @author Akhilesh
 */
interface Converter<T>{

    T convert(T entity);
    
}
