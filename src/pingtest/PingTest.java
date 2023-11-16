/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DAWOUD
 */
public class PingTest {
    static String IPAddress;
    static String Address;
    URL Url =null;
    static boolean Status,PingURLStatus;    
/**
     * @param args the command line arguments
     */
 
    public static void main(String[] args) {
        // TODO code application logic here
        char answer = 'y';

                 
        
 while(Character.toUpperCase(answer) == 'Y')       
 {
      Address=JOptionPane.showInputDialog(null, "Please Enter The Address", "Web Address Message", 3);
               //Address="172.16.7.254";
               InetAddress ADDRESS = null;
        try {
            ADDRESS = InetAddress.getByName(Address);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            IPAddress= ADDRESS.getHostAddress();  
//     try   
//            {  
//                InetAddress ADDRESS =InetAddress.getByName("www.google.com");  
//               boolean status  =ADDRESS.isReachable(3000);
//                System.out.println(" T is" + String.valueOf(status));
//           
//                
//                InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
//                    for (InetAddress address : addresses) 
//                 {
//                    if (address.isReachable(10000))
//                    {   
//                       System.out.println("Connected "+ address);
//                    }
//                    else
//                    {
//                       System.out.println("Failed "+address);
//                    }
//                 }
//                
//                
//                Thread.sleep(1000);  
//            } catch (UnknownHostException e4)   
//            {  
//                // TODO Auto-generated catch block  
//                e4.printStackTrace();  
//            } catch (IOException e4) {  
//                // TODO Auto-generated catch block  
//                e4.printStackTrace();  
//            } 
//            catch (InterruptedException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            }
//    
     
     try{

            Runtime r=Runtime.getRuntime();
            Process P=r.exec("ping "+Address);
            BufferedReader in =
            new BufferedReader(
            new InputStreamReader(P.getInputStream()));

            String currentLine = null;
            while ((currentLine = in.readLine()) != null)
            System.out.println(currentLine);

//            Status=currentLine.contains("Reply from "+IPAddress);
            int ReturnValue=P.waitFor();
            Status=(ReturnValue==0);
            System.out.println(" The destination is reachable " + String.valueOf(Status));
           // PingURLStatus=pingUrl(Address);
           // System.out.println(" Show me your Things " + String.valueOf(PingURLStatus));
            
            //Start Modification
            System.out.print("\n Do you want to continue (y or n)?");
            Scanner keyboard = new Scanner(System.in);
            answer = keyboard.next().charAt(0);            
            
            
            
            
            //End Modification
     
        }//end try
     catch(Exception e)
        
        {


        }//end catch

     
 }//end while(true)
    
    
    
    
    
    
    
    
    }//end main function
    
    
public static boolean pingUrl(final String address) {
 try {
     
     
//  System.setProperty("http.proxyHost", "myProxyServer.com");
//  System.setProperty("http.proxyPort", "80");   
  final URL url = new URL("http://" + address);
  final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
  urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds
  final long startTime = System.currentTimeMillis();
  urlConn.connect();
  final long endTime = System.currentTimeMillis();
  if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
   System.out.println("Time (ms) : " + (endTime - startTime));
   System.out.println("Ping to "+address +" was success");
   return true;
  }
  else if(urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
      return false;
 } catch (final MalformedURLException e1) {
  e1.printStackTrace();
 } catch (final IOException e) {
  e.printStackTrace();
 }
 return false;
}


}//end pingtest class
