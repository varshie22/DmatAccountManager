drop DmatAccountManager;
create database DmatAccountManager;

use DmatAccountManager;

  create table User(
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(256),
      accountNo INT UNIQUE,
      password VARCHAR(256),
      accountBalance DOUBLE,
      lastUpdateOn DATETIME DEFAULT CURRENT_TIMESTAMP
  );

   create table Share(
      id INT PRIMARY KEY AUTO_INCREMENT,
      companyName VARCHAR(256),
      price DOUBLE,
      lastUpdateOn DATETIME DEFAULT CURRENT_TIMESTAMP
   );

    create table UserShare(
        id INT PRIMARY KEY AUTO_INCREMENT,
        userId INT,
        shareId INT,
        companyName VARCHAR(256),
        shareCount INT,
        FOREIGN KEY (shareId) REFERENCES share(id),
        FOREIGN KEY (userId) REFERENCES user(id)
    );

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

INSERT INTO share (companyName , price) VALUES ("Youtube",200),
                                               ("Radisys",100),
                                               ("Google",300),
                                               ("Tesla",400),
                                               ("Amazon",500),
                                               ("TATA",800);



INSERT INTO user (name, accountNo, password)
                VALUES ("Varsha Singh", "1111", "123"),
                ("Samarth Singh", "2222", "123"),
                ("Yash Tamboli", "3333", "123"),
                ("Aastha Pandey", "4444", "123"),
                ("Karan Sarawat", "5555", "123"),
                ("Shaesta Kauser", "6666", "123");