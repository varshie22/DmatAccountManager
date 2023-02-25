package com.amazon.Model;

/*
// SQL Query to create the Table of User in Data Base.
MYSQL:
  create table User(
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(256),
      accountNo INT UNIQUE,
      password VARCHAR(256),
      accountBalance DOUBLE,
      lastUpdateOn DATETIME DEFAULT CURRENT_TIMESTAMP
  );

 */
// 1 user -> share a -> share count
//            share b - > sahre count

public class User {
    public int id;
    public String name;
    public int accountNo;
    public String password;
    public Double accountBalance;
    public String lastUpdatedOn;

    public User(int id, String name, int accountNo, String password, Double accountBalance, String lastUpdatedOn) {
        this.id = id;
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.accountBalance = accountBalance;
        this.lastUpdatedOn = lastUpdatedOn;
    }
    public User(){}

    public void prettyPrint(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name \t\t\t\t\t: "+name);
        System.out.println("Account No. \t\t\t: "+accountNo);
        System.out.println("Account Balance \t\t: \u20b9 "+accountBalance);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNo=" + accountNo +
                ", password='" + password + '\'' +
                ", accountBalance=" + accountBalance +
                ", lastUpdatedOn='" + lastUpdatedOn + '\'' +
                '}';
    }
}
