package com.amazon.Model;
//This class is The Menu For User

import com.amazon.Controller.*;
import com.amazon.Session;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    TransactionService transactionService = TransactionService.getInstance();
    UserShareService userShareService= UserShareService.getInstance();
    ShareService shareService=ShareService.getInstance();
    public static Menu menu=new Menu();
    public static Menu getInstance(){return  menu;}
    private Menu(){}
    public void loginMenu(){
        Scanner scanner=new Scanner(System.in);
        boolean mainMenuRun=true;
        while (mainMenuRun) {
            System.out.println("1 : Create D-MAT Account");
            System.out.println("2 : Login");
            System.out.println("0 : Quit");
            System.out.print("Please Enter Your Choice : ");
            int initialChoice = Integer.parseInt(scanner.nextLine());
            boolean result = false;
            User user = new User();
            if (initialChoice == 1) {
                System.out.print("Enter Your Name : ");
                user.name = scanner.nextLine();
                try {
                System.out.print("Enter Your Account Number : ");
                user.accountNo = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Your password : ");
                user.password = scanner.nextLine();
                    //Used Hashing Technique to Save the Password
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                    user.password = Base64.getEncoder().encodeToString(hash);
                }catch (NumberFormatException e){
                    System.out.println("Account Number Must be Numeric And Without Any Space. ");
                }
                catch (Exception e) {
                    System.out.println("Something Went Wrong... Please Try Again. " + e);
                }
                result = auth.registerUser(user);
                auth.updateUser(user);
                if (result) {
                    System.out.println("Profile Created Successfully.");
                } else {
                    System.out.println("Profile Creation Failed...");
                }
                continue;
            }
            else if (initialChoice == 2) {
                try {
                System.out.print("Enter Your Account Number : ");
                user.accountNo = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Your Password : ");
                user.password = scanner.nextLine();

                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                    user.password = Base64.getEncoder().encodeToString(hash);
                }catch (NumberFormatException e){
                    System.out.println("Account Number Must be Numeric and Without Any Space. ");
                }
                catch (Exception e) {
                    System.out.println("Something Went Wrong... Please Try Again. " + e);
                }
                result = auth.loginUser(user);
            } else if (initialChoice == 3) {
                System.out.println("Thank You For Using d-MAT Account Manager Application GoodBye...!");
            } else if (initialChoice==0) {
                mainMenuRun=false;
                System.out.println("Thank You For Using d-MAT Account Application GoodBye...!");

            } else {
                System.out.println("Invalid Choice Please Enter a Valid Choice.");
            }
            if (result && initialChoice ==2) {
                Session.user = user;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Welcome to Your D-MAT Account");
                System.out.println("Hello, " + user.name);
                System.out.println("Its: " + new Date());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
                boolean run=true;
                boolean exitCode = false;
                while (true) {
                    System.out.println("1 : Display D-MAT Account Details");
                    System.out.println("2 : Deposit Money");
                    System.out.println("3 : Withdraw Money");
                    System.out.println("4 : Buy Shares");
                    System.out.println("5 : Sell Shares");
                    System.out.println("6 : View Transaction Reports");
                    System.out.println("0 : Log Out");
                    System.out.print("Please Enter Your Choice : ");
                    int choice=9;
                    try {
                         choice = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                    }
                    switch (choice) {
                        case 1:
                            auth.showAccount(Session.user.id);
                            System.out.println("Total Number of Shares of Each Company. ");
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            userShareService.viewUserShares(Session.user.id);
                            System.out.println("\nDo You Want to Change Your Password ?");
                            System.out.print("Press 1 to Change Else Press 0 : ");
                            int passChoice=Integer.parseInt(scanner.nextLine());
                            if(passChoice==1){
                                System.out.print("Enter Your Password : ");
                                String password =scanner.nextLine();
                                if(!password.isEmpty()) {
                                    user.password=password;
                                    try {
                                        // Encoded to Hash i.e. SHA-256 so to match correctly
                                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                                        byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                                        user.password = Base64.getEncoder().encodeToString(hash);
                                    }catch(Exception e) {
                                        System.out.println("Something Went Wrong..."+e);
                                    }
                                }
                                if(auth.updateUser(user)){
                                    System.out.println("Password Changed Successfully.");
                                }else {
                                    System.out.println("Changing Password Failed!");
                                }
                            } else if (passChoice!=0) {
                                System.out.println("Enter a Valid Option.");
                            }

                            break;
                        case 2:
                            try {
                                transactionService.DepositMoney(Session.user.id);
                            }catch (NumberFormatException e){
                                System.out.println("Amount must be in digit\n");
                            }
                            break;
                        case 3:
                            try {
                                transactionService.withdrawMoney(Session.user.id);
                            }catch (NumberFormatException e){
                                System.out.println("Amount must be in digit\n");
                            }
                            break;
                        case 4:
                            shareService.viewAllShares();
                            try{
                                shareService.buyShare();
                            }catch (NumberFormatException e){
                                System.out.println("Choice must be in digit\n");
                            }
                            break;
                        case 5:
                            // transection type 2
                            userShareService.viewUserShares(Session.user.id);
                            try{
                            shareService.sellShare(Session.user.id);
                            }catch (NumberFormatException e){
                                System.out.println("Choice must be in digit\n");
                            }
                            break;
                        case 6:
                            transactionService.viewTransactionReport(Session.user.id);
                            break;
                        case 0:
                            System.out.println("Thank You for Using d-MAT Account User Application GoodBye...!");
                            exitCode = true;
                            break;
                        default:
                            System.out.println("Invalid choice Please Enter a Valid Choice.");
                            break;
                    }
                    if (exitCode) {
                        break;
                    }
                }
            }
            else if(initialChoice==0){
                mainMenuRun=false;
                System.out.println("Quiting The Application...");
            }
            else {
                System.out.println("Invalid Credentials, Please Try Again...!!");
            }
        }
    }
}
