package com.amazon.Model;
/*
// SQL Query to create the Table of User in Data Base.
MYSQL:
   create table Transaction(
      id INT PRIMARY KEY AUTO_INCREMENT,
      shareId INT,
      userId INT,
      shareCount INT,
      pricePerShare DOUBLE,
      transactedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
      transactionCharges DOUBLE,
      sttCharges DOUBLE,
      type INT,
      FOREIGN KEY (shareId) REFERENCES share(id),
      FOREIGN KEY (userId) REFERENCES user(id)
  );
 */
import java.util.Scanner;
public class Transaction {
    public int id;
    public int shareId;
    public int userId;
    public int shareCount;
    public Double pricePerShare;
    public String transactedOn;
    public Double transactionCharges;
    public Double sttCharges;
    public int type;// 1 : buy, 2 : sell
    public Transaction(int id, int shareId, int userId, int shareCount, Double pricePerShare, String transactedOn,
                       Double transactionCharges, Double sttCharges, int type) {
        this.id = id;
        this.shareId = shareId;
        this.userId = userId;
        this.shareCount = shareCount;
        this.pricePerShare = pricePerShare;
        this.transactedOn = transactedOn;
        this.transactionCharges = transactionCharges;
        this.sttCharges = sttCharges;
        this.type = type;
    }
    public Transaction() {}
    public void getDetails(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing Transaction Details....");

        System.out.println("Enter share ID : ");
        shareId =Integer.parseInt(scanner.nextLine());

        System.out.println("Enter user Id : " );
        userId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Share Quantity : ");
        shareCount =Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Price Per Share : \u20b9 ");
        pricePerShare = (double) Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Transaction Date : ");
        transactedOn =scanner.nextLine();

        System.out.println("Enter Transaction Charges : \u20b9 ");
        transactionCharges = (double) Integer.parseInt(scanner.nextLine());

        System.out.println("Enter STT Charges : \u20b9 ");
        sttCharges = (double) Integer.parseInt(scanner.nextLine());

    }
    public void prettyPrint(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Share ID \t\t: "+shareId);
        System.out.println("User ID \t\t: "+userId);
        System.out.println("Share Count \t\t: "+shareCount);
        System.out.println("Price Per Share : \t\t\u20b9 "+pricePerShare);
        System.out.println("Transaction On : \t\t\u20b9 "+transactedOn);
        System.out.println("STT charges : \t\t\u20b9 "+sttCharges);
        System.out.println("Type : \t\t"+type);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", shareId=" + shareId +
                ", userId=" + userId +
                ", shareCount=" + shareCount +
                ", pricePerShare=" + pricePerShare +
                ", transactedOn='" + transactedOn + '\'' +
                ", transactionCharges=" + transactionCharges +
                ", sttCharges=" + sttCharges +
                ", type=" + type +
                '}';
    }
}
