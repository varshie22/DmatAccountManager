package com.amazon.Controller;

import com.amazon.DB.*;
import com.amazon.Model.Share;
import com.amazon.Model.UserShare;
import com.amazon.Session;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class UserShareService {
    static UserShareService userShareService = new UserShareService();
    DB db=DB.getInstance();
    UserShareDAO userShareDAO=new UserShareDAO();
    ShareService shareService=ShareService.getInstance();
    Scanner scanner=new Scanner(System.in);
    public UserShareService(){}
    public static UserShareService getInstance() {
        return userShareService;
    }
    public boolean addUserShare(Share share,int shareCount){
        String sql="select * from usershare where shareId="+share.id+" and userId="+Session.user.id;
        List<UserShare> userShareList=userShareDAO.retrieve(sql);
        int result;
        if(userShareList.isEmpty()){
            UserShare userShare=new UserShare();
            userShare.companyName=share.companyName;
            userShare.shareCount=shareCount;
            userShare.shareId=share.id;
            userShare.userId= Session.user.id;
            result=userShareDAO.insert(userShare);
        }else {
            System.out.println("Updating...");
            UserShare userShare=userShareList.get(0);
            userShare.shareCount+=shareCount;
            result=userShareDAO.update(userShare);
        }
        return result>0;
    }
    public void viewUserShares(int uid){
        String sql="SELECT shareId, sum(shareCount) FROM usershare where userId="+uid+" group by shareId";
        ResultSet set=db.executeQuery(sql);
        try {
            if (!set.isBeforeFirst() ) {
                System.out.println("You Have Not Bought Any Share.\n");
                return;
            }
            while(set.next()){
                if(set.getInt(2)!=0) {
                    shareService.viewAllShares(set.getInt(1));
                    System.out.println(" | Total Shares: " + set.getInt(2));
                }
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
