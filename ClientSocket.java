package basepackage;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class ClientSocket {

	public static void main(String[] args) throws IOException {
		
		try(Socket socket=new Socket("localhost",2345)){
			Scanner inStream=new Scanner(socket.getInputStream());
			Formatter outStream=new Formatter(socket.getOutputStream());
			Scanner inputSystem=new Scanner(System.in);
			
			String className;
			String classMethod;
			boolean checkClassName=true;
			boolean checkClassMethod=true;
	        do { 
	            System.out.println("Enter Class name & method");
	            className = inputSystem.next();
	            classMethod = inputSystem.next();
	            
	            if(className.equalsIgnoreCase("NumberTest") || className.equalsIgnoreCase("StringTest")) {
	            	checkClassName=false;
	            }else {
	            	System.out.println("ClassName is invalid!");
	            }	            
	            
	            if(classMethod.equalsIgnoreCase("addNumber") || classMethod.equalsIgnoreCase("subNumber") || classMethod.equalsIgnoreCase("concatString") || classMethod.equalsIgnoreCase("equalString")) {
	            	checkClassMethod=false;
	            }else {
	            	System.out.println("ClassMethod is invalid!");
	            }
	            
	        }while(checkClassName || checkClassMethod);
            
            outStream.format(className+"\n");
            outStream.flush();
            outStream.format(classMethod+"\n");
            outStream.flush();
            System.out.println(inStream.nextLine());
            System.out.println(inStream.nextLine());
	        
		}

	}

}


