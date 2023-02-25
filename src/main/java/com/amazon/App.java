package com.amazon;

import com.amazon.DB.DB;
import com.amazon.Model.Menu;

public class App
{
    public static void main( String[] args ) {

        System.out.println("***********************************************");
        System.out.println("Welcome to D-MAT Account Management Application");
        System.out.println("***********************************************");

        Session.setCharges();
        if(args.length > 0) {
            DB.FILEPATH = args[0];
        }
        DB.getInstance().loadDriver();
        Menu menu=Menu.getInstance();
        menu.loginMenu();
        DB.getInstance().closeConnection();
    }
}


