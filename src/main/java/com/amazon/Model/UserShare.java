package com.amazon.Model;
/*
// SQL Query to create the Table of User in Data Base.
MYSQL:
  create table UserShare(
      id INT PRIMARY KEY AUTO_INCREMENT,
      userId INT,
      shareId INT,
      companyName VARCHAR(256),
      shareCount INT,
      FOREIGN KEY (shareId) REFERENCES share(id),
      FOREIGN KEY (userId) REFERENCES share(id)
  );
*/
import java.util.Scanner;

public class UserShare {
    public int id;
    public int userId;
    public int shareId;
    public String companyName;
    public int shareCount;
    public UserShare(int id, int userId, int shareId, String companyName, int shareCount) {
        this.id = id;
        this.userId = userId;
        this.shareId = shareId;
        this.companyName = companyName;
        this.shareCount = shareCount;
    }
    public UserShare() {}
    public void prettyPrint(){
        System.out.println("User Id : \t\t"+userId);
        System.out.println("Share ID : \t\t"+shareId);
        System.out.println("Company Name : \t\t"+companyName);
        System.out.println("Share count : \t\t"+shareCount);
    }
    public void getDetails(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing User Shares Details....");

        System.out.println("Enter User ID : ");
        userId=Integer.parseInt(scanner.nextLine());

        System.out.println("Enter share ID : ");
        shareId =Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Company Name : ");
        companyName =scanner.nextLine();

        System.out.println("Enter Share Quantity : ");
        shareCount =Integer.parseInt(scanner.nextLine());
    }
    @Override
    public String toString() {
        return "UserShare{" +
                "id=" + id +
                ", userId=" + userId +
                ", shareId=" + shareId +
                ", companyName='" + companyName + '\'' +
                ", shareCount=" + shareCount +
                '}';
    }
}
