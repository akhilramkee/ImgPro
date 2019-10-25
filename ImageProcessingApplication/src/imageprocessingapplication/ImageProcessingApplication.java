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
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        ImageX img = new ImageX("E:/Siva/DSC_0006.jpg","E:/Siva/a.jpg");
        ImageX img1 = new ImageX("E:/Siva/DSC_0008.jpg","E:/Siva/bt.jpg");
        final int threadCount = 3;
        final Thread[] threads = new Thread[threadCount];
        
        class filterThread extends Thread{
            
            public void run(){
                try{
                    synchronized(img1){
                        img1.medianFilter(threadCount);
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
        }
        
        try{
            img.open();
            img1.open();
        }catch(IOException e){
            System.out.println("Error opening file");
        }
        
        for(int i=0;i<threadCount;i++){
            threads[i] = new filterThread();
            threads[i].start();
        }
        
        for(int i=0;i<threadCount;i++){
            threads[i].join();
        }
        
        img.grayscaleFilter();
        
/*        try{
            img.compress("E:/b.txt");
            img.decompress("E:/b.txt", "E:/f.jpg");
        }catch(Exception e){
            System.out.println(e);
        }
*/        
        
              
        try{
            img.write();
            img1.write();
        }catch(IOException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
