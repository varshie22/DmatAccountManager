package com.amazon.DB;

import com.amazon.Model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    DB db=DB.getInstance();
    @Override
    public int insert(User object) {
        String sql= "INSERT INTO user (name, accountNo, password , accountBalance)"
                + "VALUES('"+object.name+"', "+object.accountNo+", '"+object.password+"', "+object.accountBalance+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(User object) {
        String sql = "UPDATE User set name='"+object.name+"', password='"+object.password+"', accountBalance="+object.accountBalance+
                " where id="+object.id ;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(User object) {
        String sql = "DELETE FROM User WHERE accountNo = '"+object.accountNo+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<User> retrieve() {

        String sql = "SELECT * from User";
        ResultSet set = db.executeQuery(sql);
        ArrayList<User> users = new ArrayList<User>();
        try {
            while(set.next()) {
                User user = new User();
                // Read the row from ResultSet and put the data into User object
                user.id = set.getInt("id");
                user.name = set.getString("name");
                user.accountNo = set.getInt("accountNo");
                user.password = set.getString("password");
                user.accountBalance = set.getDouble("accountBalance");
                user.lastUpdatedOn = set.getString("lastUpdateOn");
                users.add(user);
            }
        }catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return users;
    }
    @Override
    public List<User> retrieve(String sql){
        ResultSet set = db.executeQuery(sql);
        ArrayList<User> users = new ArrayList<User>();
        try {
            while(set.next()) {
                User user = new User();
                user.id = set.getInt("id");
                user.name = set.getString("name");
                user.accountNo = set.getInt("accountNo");
                user.password = set.getString("password");
                user.accountBalance = set.getDouble("accountBalance");
                user.lastUpdatedOn = set.getString("lastUpdateOn");
                users.add(user);
            }
        }catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return users;
    }
}
