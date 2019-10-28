/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Akhilesh
 */
class filterThread extends Thread{
            
            ImageEditor imageRef;
            int threadCount;
            int filteroption;
            
            filterThread(ImageEditor objectref,int Count,int filter){
                imageRef = objectref;
                threadCount = Count;
                filteroption = filter;
            }
            
            public void run(){
                try{
                    synchronized(imageRef){
                        switch(filteroption){
                            case 1:imageRef.convertToSepia(threadCount);
                                    break;
                            case 2:imageRef.grayscaleFilter(threadCount);
                                    break;
                            case 4:imageRef.convertToNegative(threadCount);
                                    break;
                            case 3:imageRef.convertToMirror(threadCount);
                                    break;
                            case 5:imageRef.medianFilter(threadCount);
                                    break;
                        }
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }
}




public class ImageProcessingApplication {

    /**
     * @param args the command line arguments
     */
    public Integer printOptions(){
        System.out.println("========================================================");
        System.out.println("                 IMAGE FILTER APPLICATION               ");
        System.out.println("========================================================");
        System.out.println("     1. Demo of a Non-Thread Image Editor");
        System.out.println("     2. Demo of a Thread based Editor    ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    
    public Integer chooseFilters(){
        System.out.println("========================================================");
        System.out.println("                   FILTER OPTIONS AVAILABLE             ");
        System.out.println("========================================================");
        System.out.println("     1.Sepia  2.GrayScale 3.Mirror  4.Negative 5.Median ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    
    public Integer returnTypeFile(){
        System.out.println("========================================================");
        System.out.println("   1.Compressed File        2.Uncompressed File         ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    
    
    public String getFileName(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    
    public void OverlayStream(Integer FilterOption){
        
        Integer ThreadCount;
        System.out.println("Enter the Source File:");
        String inputFile = getFileName();
        ImageEditor imageFile = new ImageEditor(inputFile);
        System.out.println("Enter the Type of OutputFile:");
        Integer type = returnTypeFile();
        System.out.println("Output File with extension:");
        String outputFile = getFileName();
        
        
        try{
            imageFile.open();
        }catch(IOException e){
            System.out.println("Error opening file");
        }
        
        try{
            switch(FilterOption){
                case 1:if(type == 1){
                    imageFile.convertToSepia();
                    imageFile.compress(outputFile);
                }else{
                    imageFile.convertToSepia();
                    imageFile.write(outputFile);
                }
                break;
                    
                case 2:if(type == 1){
                    imageFile.grayscaleFilter();
                    imageFile.compress(outputFile);
                }else{
                    imageFile.grayscaleFilter();
                    imageFile.write(outputFile);
                }
                break;
                    
                case 3:if(type == 1){
                    imageFile.convertToMirror();
                    imageFile.compress(outputFile);
                }else{
                    imageFile.convertToMirror();
                    imageFile.write(outputFile);
                }
                break;
                    
                case 4:if(type == 1){
                    imageFile.convertToNegative();
                    imageFile.compress(outputFile);
                }else{
                    imageFile.convertToNegative();
                    imageFile.write(outputFile);
                }
                break;
                    
                case 5:if(type == 1){
                    imageFile.medianFilter();
                    imageFile.compress(outputFile);
                }else{
                    imageFile.medianFilter();
                    imageFile.write(outputFile);
                }
                break;
               
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
       
        
        System.out.println("Do you want to decompress a compressed file / Edit a new File?(1/2):");
        Scanner in  = new Scanner(System.in);
        Integer Mode = in.nextInt();
        ImageProcessingApplication application  = new ImageProcessingApplication();
        
        if(Mode == 2){
            Integer Options = application.printOptions();
            Integer FilterType = application.chooseFilters();
            Integer ThreadCount;
            try{
                if(Options == 2){
                    System.out.println("Enter the number of threads:");
                    ThreadCount = in.nextInt();
                    System.out.println("Enter the Source File:");
                    String inputFile = application.getFileName();
                    ImageEditor imageFile = new ImageEditor(inputFile);
                    System.out.println("Enter the Type of OutputFile:");
                    Integer type = application.returnTypeFile();
                    System.out.println("Output File with extension:");
                    String outputFile = application.getFileName();
                    try{
                        imageFile.open();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    final Thread[] threads = new Thread[ThreadCount];
                    for(int i=0;i<ThreadCount;i++){
                        threads[i] = new filterThread(imageFile,ThreadCount,FilterType);
                        threads[i].start();
                    }
                    for(int i=0;i<ThreadCount;i++){
                        threads[i].join();
                    }
                    if(type == 2){
                        imageFile.write(outputFile);
                    }else{
                        imageFile.compress(outputFile);
                    }
                }else{
                    application.OverlayStream(FilterType);
                }
            }catch(ImageNotLoadedException e){
                System.out.println("Image should be loaded before writing");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Enter the File to be loaded");
            String input = in.next();
            System.out.println("Enter the File location to store");
            String out = in.next();
            ImageEditor ed = new ImageEditor();
            try{
                ed.decompress(input, out);
            }catch(Exception fe){
                System.out.println(fe.getMessage());
            }
        }
    }
    
}
