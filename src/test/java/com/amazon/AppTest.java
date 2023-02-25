package com.amazon;

import com.amazon.DB.DB;
import com.amazon.DB.UserShareDAO;
import com.amazon.Model.User;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;
import com.amazon.Model.*;
import com.amazon.Controller.AuthenticationService;


public class AppTest {
    AuthenticationService authenticationService=AuthenticationService.getInstance();
    UserShareDAO userShareDAO=new UserShareDAO();

    public AppTest(){
        DB.getInstance().loadDriver();

    }

    @Test
    public void testUserLogin(){
        User user = new User();
        user.accountNo =4248;
        user.password = "a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=";
        boolean result=authenticationService.loginUser(user);
        System.out.println(result);
        Assert.assertEquals(true,result);

    }

    @Test
    public void testUserRegister(){
        User user = new User();
        user.accountNo =9999;
        user.name="samuels";
        user.password = "abc";
        boolean result=authenticationService.registerUser(user);
        System.out.println(result);
        Assert.assertEquals(true,result);

    }

    @Test
    public void testAddingUserShare(){
        //to test if usershare has a valid user id and share id which exist in the database
        UserShare userShare=new UserShare();
        userShare.userId=1;
        userShare.shareId=6;
        userShare.companyName="TATA";
        userShare.shareCount=2;
        int result=userShareDAO.insert(userShare);
        Assert.assertEquals(true,result>0);

    }




}