package com.amazon;

import com.amazon.Model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Session {
    public static double transactionCharge=0.05;
    public static double stt=0.01;

    public static User user = null;
    public static void setCharges(){
        try {
            File file = new File("src/main/java/com/amazon/charges.txt");
            if(file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);

                transactionCharge = Double.parseDouble(buffer.readLine());
                stt = Double.parseDouble(buffer.readLine());

                buffer.close();
                reader.close();
                System.out.println("Charges Configured Using The File.");
            }else {
                System.err.println("Cannot Read The db Config File.......");
            }
        }catch(Exception e) {
            System.out.println("Something Went Wrong"+e);
        }
    }
}
