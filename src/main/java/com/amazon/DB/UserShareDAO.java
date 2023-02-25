package com.amazon.DB;

import com.amazon.Model.UserShare;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class UserShareDAO implements DAO<UserShare>{
    DB db=DB.getInstance();
    @Override
    public int insert(UserShare object) {
        String sql= "INSERT INTO UserShare (userId, shareId, companyName, shareCount)"
                + "VALUES("+object.userId+", "+object.shareId+", '"+object.companyName+"', "+object.shareCount+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(UserShare object) {
        String sql = "UPDATE UserShare set shareCount="+object.shareCount+" where id="+object.id ;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(UserShare object) {
        String sql = "DELETE FROM UserShare WHERE userId = '"+object.userId+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<UserShare> retrieve() {

        String sql = "SELECT * from UserShare";
        ResultSet set = db.executeQuery(sql);
        ArrayList<UserShare> userShares = new ArrayList<UserShare>();
        try {
            while(set.next()) {
                UserShare userShare = new UserShare();
                // Read the row from ResultSet and put the data into User object
                userShare.id = set.getInt("id");
                userShare.shareId = set.getInt("shareId");
                userShare.companyName = set.getString("companyName");
                userShare.shareCount = set.getInt("shareCount");
                userShares.add(userShare);
            }
        }catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return userShares;
    }
    @Override
    public List<UserShare> retrieve(String sql){
        ResultSet set = db.executeQuery(sql);
        ArrayList<UserShare> userShares = new ArrayList<UserShare>();
        try {
            while(set.next()) {
                UserShare userShare = new UserShare();
                // Read the row from ResultSet and put the data into User object
                userShare.id = set.getInt("id");
                userShare.shareId = set.getInt("shareId");
                userShare.companyName = set.getString("companyName");
                userShare.shareCount = set.getInt("shareCount");
                userShares.add(userShare);
            }
        }catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return userShares;
    }
}
