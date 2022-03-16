package org.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {

    //try-catch block included to catch the IO Exception which might be raised by readLine() method
    public static String inputFromUser() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input="";
        try {
            input = br.readLine();
        }catch(IOException e){
            System.out.println("IO Exception thrown.Please try again.");
        }
        return input;
    }
}
