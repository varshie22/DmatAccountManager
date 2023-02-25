package com.amazon.Model;
/*
// SQL Query to create the Table of User in Data Base.
MYSQL:
  create table Share(
      id INT PRIMARY KEY AUTO_INCREMENT,
      companyName VARCHAR(256),
      price DOUBLE,
      lastUpdateOn DATETIME DEFAULT CURRENT_TIMESTAMP
  );

 */
public class Share {
    public int id;
    public String companyName;
    public double price;
    public String lastUpdateOn;
    public Share(int id, String companyName, double price, String lastUpdateOn) {
        this.id = id;
        this.companyName = companyName;
        this.price = price;
        this.lastUpdateOn = lastUpdateOn;
    }
    public Share(){}
    public void prettyPrint(){
        System.out.println("Id : \t\t\t\t\t"+id);
        System.out.println("Company Name : \t\t\t"+companyName);
        System.out.println("Share Price :  \t\t\t \u20b9 "+price + " (Per Share)");
        System.out.println("Last Updated On: \t\t"+lastUpdateOn);
    }
    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", lastUpdateOn='" + lastUpdateOn + '\'' +
                '}';
    }
}
