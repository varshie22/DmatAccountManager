package com.amazon.Controller;

import com.amazon.DB.DB;
import com.amazon.DB.ShareDAO;
import com.amazon.DB.UserDAO;
import com.amazon.DB.UserShareDAO;
import com.amazon.Model.Share;
import com.amazon.Model.UserShare;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class ShareService {
    private static ShareService shareService =new ShareService();
    private ShareService(){}
    DB db=DB.getInstance();
    UserShareDAO userShareDAO=new UserShareDAO();
    TransactionService transactionService=new TransactionService();
    ShareDAO shareDAO=new ShareDAO();
    Scanner scanner=new Scanner(System.in);
    public static ShareService getInstance() {
        return  shareService;
    }
    public void viewAllShares(){
        String sql="select * from share";
        List<Share> shareList=shareDAO.retrieve();
        for (Share share:shareList){
            share.prettyPrint();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    public void viewAllShares(int id){
        String sql="select * from share where id="+id;
        List<Share> shareList=shareDAO.retrieve(sql);
        for (Share share:shareList){
            System.out.print("Share ID: "+share.id+" | Company name: "+share.companyName);
        }
    }
    public void buyShare() throws NumberFormatException {

        System.out.println("Enter Share ID : ");
        int shareId=Integer.parseInt(scanner.nextLine());
        String sql="select * from share where id="+shareId;
        List<Share> shareList=shareDAO.retrieve(sql);
        if(shareList.isEmpty()){
            System.out.println("Please Enter a Valid Share ID : ");
            return;
        }
        Share share=shareList.get(0);
        System.out.print("How Many Shares You Want to Buy? : ");
        int shareCount=Integer.parseInt(scanner.nextLine());
        transactionService.makePayment(share,shareCount);
    }
    public void sellShare (int uid) throws NumberFormatException {
        System.out.println("Enter Share ID : ");
        int shareId=Integer.parseInt(scanner.nextLine());

        String sql="select * from usershare where shareId="+shareId+" and userId="+ uid;
        List<UserShare> userShareList=userShareDAO.retrieve(sql);
        if(userShareList.isEmpty()){
            System.out.println("Please Enter a valid Share ID : ");
            return;
        }
        System.out.print("How Many Shares You Want to Sell?  : ");
        int shareCount=Integer.parseInt(scanner.nextLine());
        //check the shares available
        sql="select sum(shareCount) from usershare where userId="+uid+" and shareId="+shareId+" group by shareId="+shareId;
        ResultSet set=db.executeQuery(sql);
        try {
            while (set.next()){
                if(set.getInt(1)<shareCount){
                    System.out.println("Please Enter Share Count lesser than " + shareCount);
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Share> shareList=shareDAO.retrieve("select * from share where id="+shareId);
        Share share=shareList.get(0);
        if(transactionService.receivePayment(share,shareCount)) {
            UserShare userShare = userShareList.get(0);
            userShare.shareCount -= shareCount;
            userShareDAO.update(userShare);
        }
    }
}
