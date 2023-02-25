package com.amazon.DB;

import com.amazon.Model.Transaction;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements DAO<Transaction>{

    DB db=DB.getInstance();
    @Override
    public int insert(Transaction object) {
        String sql= "INSERT INTO transaction (shareId, userId, shareCount, pricePerShare, transactionCharges, sttCharges, type)"
                + "VALUES("+object.shareId+", "+object.userId+", "+object.shareCount+", "+object.pricePerShare+", "+object.transactionCharges+", "+object.sttCharges+", "+object.type+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Transaction object) {
        return 0;
    }

    @Override
    public int delete(Transaction object) {
        return 0;
    }

    @Override
    public List<Transaction> retrieve() {
        String sql = "SELECT * from transaction";
        ResultSet set = db.executeQuery(sql);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            while(set.next()) {
                Transaction transaction=new Transaction();
                // Read the row from ResultSet and put the data into User object
                transaction.id = set.getInt("id");
                transaction.shareId = set.getInt("shareId");
                transaction.userId = set.getInt("userId");
                transaction.pricePerShare = set.getDouble("pricePerShare");
                transaction.shareCount = set.getInt("shareCount");
                transaction.transactedOn = set.getString("transactedOn");
                transaction.transactionCharges = set.getDouble("transactionCharges");
                transaction.sttCharges = set.getDouble("sttCharges");
                transaction.type = set.getInt("type");
                transactions.add(transaction);
            }
        }
        catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            while(set.next()) {
                Transaction transaction=new Transaction();
                // Read the row from ResultSet and put the data into User object
                transaction.id = set.getInt("id");
                transaction.shareId = set.getInt("shareId");
                transaction.userId = set.getInt("userId");
                transaction.pricePerShare = set.getDouble("pricePerShare");
                transaction.shareCount = set.getInt("shareCount");
                transaction.transactedOn = set.getString("transactedOn");
                transaction.transactionCharges = set.getDouble("transactionCharges");
                transaction.sttCharges = set.getDouble("sttCharges");
                transaction.type = set.getInt("type");
                transactions.add(transaction);
            }
        }
        catch(Exception e) {
            System.err.println("Something Went Wrong...."+e);
        }
        return transactions;
    }
}
