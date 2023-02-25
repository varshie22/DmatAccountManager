package com.amazon.DB;

import com.amazon.Model.Share;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShareDAO implements DAO<Share>{
    DB db=DB.getInstance();
    @Override
    public int insert(Share object) {
        //Shares Inserted manually
        return 0;
    }
    @Override
    public int update(Share object) {
        return 0;
    }
    @Override
    public int delete(Share object) {
        return 0;
    }
    @Override
    public List<Share> retrieve() {
        String sql = "SELECT * from share";
        ResultSet set = db.executeQuery(sql);
        ArrayList<Share> shares = new ArrayList<Share>();
        try {
            while(set.next()) {
                Share share = new Share();
                // Read the row from ResultSet and put the data into User object
                share.id = set.getInt("id");
                share.companyName = set.getString("companyName");
                share.price = set.getDouble("price");
                share.lastUpdateOn = set.getString("lastUpdateOn");
                shares.add(share);
            }
        }
        catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return shares;
    }

    @Override
    public List<Share> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Share> shares = new ArrayList<Share>();
        try {
            while(set.next()) {
                Share share = new Share();
                // Read the row from ResultSet and put the data into User object
                share.id = set.getInt("id");
                share.companyName = set.getString("companyName");
                share.price = set.getDouble("price");
                share.lastUpdateOn = set.getString("lastUpdateOn");
                shares.add(share);
            }
        }
        catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return shares;
    }
}
