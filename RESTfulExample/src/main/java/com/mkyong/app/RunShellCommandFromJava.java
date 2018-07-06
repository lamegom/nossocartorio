package com.mkyong.app;

//file: RunShellCommandFromJava.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RunShellCommandFromJava {

 public static void main(String[] args) {}
 
 public String run(String cpf) throws Exception{

//     Runtime rt = Runtime.getRuntime();
//     String[] commands = {"sh",  "callX.sh " , cpf, "/home/ubuntu/verifchecks/"};
//     Process proc = rt.exec(commands);

	 ProcessBuilder pb = new ProcessBuilder("sh", "/home/ubuntu/verifchecks/callX.sh", cpf);
	 Process p = pb.start();

	 ProcessWithTimeout processWithTimeout = new ProcessWithTimeout(p);
	 int exitCode = processWithTimeout.waitForProcess(300000);
	 
	 StringBuffer sb = new StringBuffer();

	 if (exitCode == Integer.MIN_VALUE)
	 {
	     sb.append("Timeout");// Timeout
	     p.destroyForcibly();
	 }
	 else
	 {
	     // No timeout !

	 
     BufferedReader stdInput = new BufferedReader(new 
          InputStreamReader(p.getInputStream()));

     BufferedReader stdError = new BufferedReader(new 
          InputStreamReader(p.getErrorStream()));
     
     

     // read the output from the command
     System.out.println("Here is the standard output of the command:\n");
     String s = null;
     int i = 0;
     while ((s = stdInput.readLine()) != null) {
         System.out.println(s);
         if(s.indexOf("Exception")>0) throw new Exception("Erro no selenium");
         if(i>0)sb.append(s);
         i++;
     }

     // read any errors from the attempted command
     System.out.println("Here is the standard error of the command (if any):\n");
     while ((s = stdError.readLine()) != null) {
         System.out.println(s);
         sb.append(s);
     }
     
     
     
 }
     String str = sb.toString();
//     str = str.substring(str.indexOf(System.getProperty("line.separator"))+1);
     
     Document doc = Jsoup.parse(str);
     
     String response = doc.html();
     
     return response.replaceAll("INFO: Detected dialect: W3C", "");

 }
 
 public String simulate(String valor) throws Exception{

//   Runtime rt = Runtime.getRuntime();
//   String[] commands = {"sh",  "callX.sh " , cpf, "/home/ubuntu/verifchecks/"};
//   Process proc = rt.exec(commands);

	 ProcessBuilder pb = new ProcessBuilder("sh", "/home/ubuntu/verifchecks/callXSim.sh", valor);
	 Process p = pb.start();

	 ProcessWithTimeout processWithTimeout = new ProcessWithTimeout(p);
	 int exitCode = processWithTimeout.waitForProcess(300000);
	 
	 StringBuffer sb = new StringBuffer();

	 if (exitCode == Integer.MIN_VALUE)
	 {
	     sb.append("Timeout");// Timeout
	     p.destroyForcibly();
	 }
	 else
	 {
	     // No timeout !

	 
   BufferedReader stdInput = new BufferedReader(new 
        InputStreamReader(p.getInputStream()));

   BufferedReader stdError = new BufferedReader(new 
        InputStreamReader(p.getErrorStream()));
   
   

   // read the output from the command
   System.out.println("Here is the standard output of the simulation:\n");
   String s = null;
   int i = 0;
   while ((s = stdInput.readLine()) != null) {
       System.out.println(s);
       if(s.indexOf("Exception")>0) throw new Exception("Erro no selenium");
       if(i>0)sb.append(s);
       i++;
   }

   // read any errors from the attempted command
   System.out.println("Here is the standard error of the simulation (if any):\n");
   while ((s = stdError.readLine()) != null) {
       System.out.println(s);
       sb.append(s);
   }
   
   
   
}
   String str = sb.toString();
//   str = str.substring(str.indexOf(System.getProperty("line.separator"))+1);
   
   Document doc = Jsoup.parse(str);
   
   String response = doc.html();
   
   return response.replaceAll("INFO: Detected dialect: W3C", "");

}
}



