
package imageprocessingapplication;
import java.awt.image.BufferedImage;

/**
 *
 * Interface class extending Filter that works for BufferedImage objects
 * Has declaration for both thread and non thread based implementation
 * 
 * @author Akhilesh
 * @author Sai
 * 
 */
interface ImageFilter extends Filter<BufferedImage>{
    
    @Override
    public BufferedImage filter(BufferedImage img);
    
    public BufferedImage filter(BufferedImage img,int threadPosition,int totalThreads);
    
}
