package helloworld;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AsciiArt {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            FileReader reader=new FileReader("\\Users\\SMART WAY\\Desktop\\monaLisa.txt");
            int data=reader.read();
            while(data!=-1){
                System.out.print((char)data);
                data=reader.read();             
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
