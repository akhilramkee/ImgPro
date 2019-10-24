/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.io.IOException;
/**
 *
 * @author Akhilesh
 */
public class ImageProcessingApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageX img = new ImageX("E:/Siva/DSC_0006.jpg","E:/Siva/a.jpg");
       
        try{
            img.open();
        }catch(IOException e){
            System.out.println("Error opening file");
        }
        
        img.medianFilter();
        
        try{
            img.write();
        }catch(IOException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
