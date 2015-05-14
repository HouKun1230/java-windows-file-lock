package FileLock;

import java.io.File;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.nio.channels.FileChannel;  
import java.nio.channels.FileLock;  
import java.util.Calendar;

public class write extends Thread {
	
	 public void run(){  
	        Calendar calstart=Calendar.getInstance();  
	        File file=new File("test.txt");          
	        try {  
	            if(!file.exists())  
	                file.createNewFile();  
	                          
	          
	            RandomAccessFile out = new RandomAccessFile(file, "rw");  
	            FileChannel fcout=out.getChannel();  
	            FileLock flout=null;  
	            while(true){    
	                try {  
	                    flout = fcout.tryLock();  
	                    break;  
	                } catch (Exception e) {  
	                     System.out.println("This file is being using");   
	                     sleep(1000);    
	                }  
	                  
	            }  
	          
	            for(int i=1;i<=1000;i++){  
	                sleep(10);  
	                StringBuffer sb=new StringBuffer();  
	                sb.append("This is "+i+" lane \n");  
	                out.write(sb.toString().getBytes("utf-8"));  
	            }  
	              
	            flout.release();  
	            fcout.close();  
	            out.close();  
	            out=null;  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        Calendar calend=Calendar.getInstance();  
	        System.out.println("Spending "+(calend.getTimeInMillis()-calstart.getTimeInMillis())+" sconds");  
	    }  

}
