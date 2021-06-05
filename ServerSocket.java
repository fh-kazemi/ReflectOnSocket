package basepackage;

import java.io.*;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class ServerSocket {

    public static void main(String[] args) throws IOException {

        NumberTest num1 = new NumberTest(3, 5);
        NumberTest num2 = new NumberTest(4, 6);

        StringTest st1 = new StringTest("Ali", "Reza");
        StringTest st2 = new StringTest("Mohammad", "Parsa");

        FileOutputStream file1 = new FileOutputStream("E:\\Serializable.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(file1);

        outputStream.writeObject(num1);
        outputStream.writeObject(num2);
        outputStream.writeObject(st1);
        outputStream.writeObject(st2);
        outputStream.close();

        //********************************************************

        try(java.net.ServerSocket server=new java.net.ServerSocket(2345)){
            Socket socket=server.accept();
            Scanner inStream=new Scanner(socket.getInputStream());
            Formatter outStream=new Formatter(socket.getOutputStream());
            Scanner input=new Scanner(System.in);

            String className=inStream.nextLine();
            String classMethod=inStream.nextLine();

            FileInputStream file2 = new FileInputStream("E:\\Serializable.txt");
            ObjectInputStream inputStream = new ObjectInputStream(file2);

            Object object;
            do {
                try {
                    object = inputStream.readObject();
                    if (object instanceof basepackage.NumberTest && className.equalsIgnoreCase("NumberTest")) {
                        if (classMethod.equalsIgnoreCase("addNumber")) {
                            outStream.format( object.getClass().getMethod(classMethod).invoke(object)+ "\n");
                            outStream.flush();

                        }else if (classMethod.equalsIgnoreCase("subNumber")) {
                            outStream.format( object.getClass().getMethod(classMethod).invoke(object)+ "\n");
                            outStream.flush();

                        }
                    }else if (object instanceof basepackage.StringTest && className.equalsIgnoreCase("StringTest")) {
                        if (classMethod.equalsIgnoreCase("concatString")) {
                            outStream.format( object.getClass().getMethod(classMethod).invoke(object)+ "\n");
                            outStream.flush();

                        }else if (classMethod.equalsIgnoreCase("equalString")) {
                            outStream.format( object.getClass().getMethod(classMethod).invoke(object)+ "\n");
                            outStream.flush();
                        }
                    }
                }catch (Exception e) {
                    break;
                }
            } while (true);
        }

    }
}



class NumberTest implements Serializable {
    int num1;
    int num2;
    public NumberTest(int num1,int num2){
        this.num1=num1;
        this.num2=num2;
    }
    public int addNumber(){
        return num1+num2;
    }

    public int subNumber(){
        return num1-num2;
    }
}

class StringTest implements Serializable{
    String st1;
    String st2;

    public StringTest(String st1,String st2){
        this.st1=st1;
        this.st2=st2;
    }

    public String concatString(){
        return st1.concat(st2);
    }

    public boolean equalString(){
        return st1.equals(st2);
    }
}
