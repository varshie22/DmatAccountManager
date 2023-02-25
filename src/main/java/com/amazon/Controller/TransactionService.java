package com.amazon.Controller;

import com.amazon.DB.ShareDAO;
import com.amazon.DB.TransactionDAO;
import com.amazon.DB.UserDAO;
import com.amazon.Model.Share;
import com.amazon.Model.Transaction;
import com.amazon.Model.User;
import com.amazon.Session;
import java.util.List;
import java.util.Scanner;
public class TransactionService {
    static TransactionService transactionService = new TransactionService();
    AuthenticationService authenticationService=AuthenticationService.getInstance();
    TransactionDAO transactionDAO = new TransactionDAO();
    ShareDAO shareDAO=new ShareDAO();
    UserShareService userShareService=new UserShareService();
    UserDAO userDAO=new UserDAO();
    Scanner scanner=new Scanner(System.in);
    public TransactionService(){}
    public static TransactionService getInstance() {
        return transactionService;
    }
    public boolean debit(double amount){
        String sql="select * from user where id="+Session.user.id;
        List<User> userList=userDAO.retrieve(sql);
        User user=userList.get(0);
        if(amount>user.accountBalance){
            System.out.println("Insufficient Balance in Your Account...");
            return false;
        }
        user.accountBalance=user.accountBalance-amount;
        int result=userDAO.update(user);
        if(result>0){
            System.out.println("Amount : \u20b9 "+amount+" Debited.");
            System.out.println("Remaining Balance : \u20b9 "+user.accountBalance);
        }
        else {
            System.out.println("Transaction Failed...");
            return false;
        }
        return true;
    }
    public void DepositMoney(int uid) throws NumberFormatException{
        String sql="select * from user where id="+uid;
        List<User> userList=userDAO.retrieve(sql);
        if(userList.isEmpty()){
            System.out.println("Something Went Wrong... Please Try Again");
            return;
        }
        User user=userList.get(0);
        System.out.println("Current Balance : \u20b9 "+user.accountBalance);
        System.out.print("Enter the Amount to be Deposit : ");
        double deposit=Double.parseDouble(scanner.nextLine());
        user.accountBalance+=deposit;
        if(userDAO.update(user)>0) {
            System.out.println("\nAmount Deposited Successfully.");
            System.out.println("Available Balance : \u20b9 "+user.accountBalance+'\n');
            Session.user=user;
        }else{
            System.out.println("Depositing Amount Unsuccessful.");
        }
    }
    public void withdrawMoney(int uid) throws NumberFormatException{
        String sql="select * from user where id="+uid;
        List<User> userList=userDAO.retrieve(sql);
        if(userList.isEmpty()){
            System.out.println("Something Went Wrong Please Try Again.");
            return;
        }
        User user=userList.get(0);
        System.out.println("Current Balance : \u20b9 "+user.accountBalance);
        System.out.print("Enter the Amount to be Withdraw : ");
        double withdraw=Double.parseDouble(scanner.nextLine());
        if(withdraw>user.accountBalance){
            System.out.println("You Can't Withdraw This Much Amount...");
            return;
        }
        user.accountBalance-=withdraw;
        if(userDAO.update(user)>0) {
            System.out.println("\nAmount Withdrawn Successfully.");
            System.out.println("Available Balance : \u20b9 "+user.accountBalance+'\n');
        }else{
            System.out.println("Withdrawing Amount Unsuccessful...");
        }
    }
    public void makePayment(Share share, int shareCount){

        double transectionCharge=(shareCount*share.price)* (Session.transactionCharge/100);
        if(transectionCharge<100)
            transectionCharge=100;
        String sql="select * from transaction where userId="+Session.user.id;
        List<Transaction> transactions=transactionDAO.retrieve(sql);
        double total=share.price*shareCount;
        for(Transaction transaction:transactions){
            total+=transaction.pricePerShare*transaction.shareCount;
        }
        double sttCharge= total * Session.stt / 100;

        double totalCost= shareCount*share.price+transectionCharge+sttCharge;

        if(debit(totalCost)==false){
            return;
        }
        Transaction transaction=new Transaction();
        transaction.type=1;
        transaction.userId=Session.user.id;
        transaction.sttCharges=sttCharge;
        transaction.shareCount=shareCount;
        transaction.shareId=share.id;
        transaction.transactionCharges=transectionCharge;
        transaction.pricePerShare=share.price;
        int result=transactionDAO.insert(transaction);
        if(result>0) {
            if(userShareService.addUserShare(share,shareCount)==false){
                System.out.println("Something Went Wrong..");
                return;
            }
            System.out.println("Transaction Completed.\n");
        }
        else{
            System.out.println("Transaction Failed!");
        }
    }
    public boolean receivePayment(Share share, int shareCount) {
        double transectionCharge=(shareCount*share.price)* (Session.transactionCharge/100);
        if(transectionCharge<100)
            transectionCharge=100;
        String sql="select * from transaction where userId="+Session.user.id;
        List<Transaction> transactions=transactionDAO.retrieve(sql);
        double total=share.price*shareCount;
        for(Transaction transaction:transactions){
            total+=transaction.pricePerShare*transaction.shareCount;
        }
        double sttCharge= total * Session.stt / 100;

        double totalCost= shareCount*share.price-transectionCharge-sttCharge;

        Transaction transaction=new Transaction();
        transaction.type=2;
        transaction.userId=Session.user.id;
        transaction.sttCharges=sttCharge;
        transaction.shareCount=shareCount;
        transaction.shareId=share.id;
        transaction.transactionCharges=transectionCharge;
        transaction.pricePerShare=share.price;
        int result=transactionDAO.insert(transaction);
        if(result>0) {
            if(credit(totalCost)==false){
                return false;
            }
            System.out.println("Transaction Successful!");
            return true;
        }
        return false;
    }
    private boolean credit(double totalCost) {
        String sql="select * from user where id="+Session.user.id;
        List<User> userList=userDAO.retrieve(sql);
        User user=userList.get(0);
        user.accountBalance+=totalCost;
        if(authenticationService.updateUser(user)){
            System.out.println("Amount Credited.");
            return true;
        }
        return  false;
    }
    public void viewTransactionReport(int uid) {
        String sql="select * from transaction where userId="+uid;
        List<Transaction> transactionList=transactionDAO.retrieve(sql);
        System.out.println("Debited Transactions :\n");
        for(Transaction transaction:transactionList){
            if(transaction.type==1){//buy
                sql="select * from share where id="+transaction.shareId;
                List<Share> share=shareDAO.retrieve(sql);
                System.out.println("Company Name : "+ share.get(0).companyName);
                System.out.println("Shares Bought : "+ transaction.shareCount);
                System.out.println("Transaction Done On : "+ transaction.transactedOn);
                System.out.println("STT Charges : \u20b9 "+transaction.sttCharges);
                System.out.println("Transaction Charges : \u20b9 "+transaction.transactionCharges);
                double total=transaction.shareCount*transaction.pricePerShare+transaction.transactionCharges+transaction.sttCharges;
                System.out.println("Total Price Paid : \u20b9 "+total);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        System.out.println("Credited Transactions :\n");
        for(Transaction transaction:transactionList){
            if(transaction.type==2){//buy
                sql="select * from share where id="+transaction.shareId;
                List<Share> share=shareDAO.retrieve(sql);
                System.out.println("Company Name : "+ share.get(0).companyName);
                System.out.println("Shares Sold : "+ transaction.shareCount);
                System.out.println("Transaction Done On : "+ transaction.transactedOn);
                System.out.println("STT Charges : \u20b9 "+transaction.sttCharges);
                System.out.println("Transaction Charges :  \u20b9 "+transaction.transactionCharges);
                double total=transaction.shareCount*transaction.pricePerShare+transaction.transactionCharges+transaction.sttCharges;
                System.out.println("Total Money Received : \u20b9 "+total);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }
}
