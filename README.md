
# Car Rental App

This project is about a simple car rental app written in Java (Java Swing). The program is connected to a MySQL Database server to verify the user's credentials. It then displays the list of available cars to rent, their type, price per day and rating.
The user has the option to choose to rent one of the cars by pressing the "Rent" button. The total price is being calculated by the amount of days typed, and then the user proceeds to the payment procedure. 

Finally the user has three options: 
- Rate the car
- Cancel payment 
- Exit the app

(The car rating function hasn't been executed in code yet.)


## MySQL Database connection

In the following code section the MySQL connection is being established and the user's credentials which were typed in the text fields (now stored as variables), are being compared to the ones from the database (which were extracted to variables from the command ```SELECT * FROM users WHERE ID = " + IdGiven``` and the use of the method 'getString').

### Code for connecting to MySQL server

```try{  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/car","root","root");
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT * FROM users WHERE ID = " + IdGiven);
                while(rs.next()){
                    if (rs.getString(2).equals(usernameGiven) && rs.getString(1).equals(IdGiven) && rs.getString(3).equals(passwordGiven)) {
                        mainScreen();
                        break;
                } else {
                    
                    constraints.gridy = 9;
                    JLabel failLabel = new JLabel("Login failed");
                    frame.add(failLabel, constraints);
                    check();
                    break;
                    }
                }
                con.close();  
           }    catch(ClassNotFoundException | SQLException b){ System.out.println(e);}
```

In order to connect the MySQL Database with the Java code,  Connector/J is required to be installed in the 'lib' folder of the Java project. Connector/J implements the Java Database Connectivity (JDBC) API and can be installed from the following link: https://dev.mysql.com/downloads/connector/j/ .



## How to run

In order to run the app so that it validates the user's credentials and work properly, apart from Connector/J, you must also have a MySQL Database server running with the following settings: 
- localhost: 3306
- username: root
- password: root

and a database 'car' created by the code below: 

```DROP DATABASE IF EXISTS car;
CREATE DATABASE car;
USE car;
CREATE TABLE users(ID varchar(24) NOT NULL, username varchar(255), password varchar(255), PRIMARY KEY(ID));
INSERT INTO users VALUES ("123", "noah" , "noah123");
INSERT INTO users VALUES ("333", "william" , "william333");
INSERT INTO users VALUES ("999", "emma" , "emma999");
SELECT * FROM users;
```

If you have a different localhost, username or/and password you can always edit the code to work for your enviroment just by changing the values you want in the following line: 
``` Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","root");```

The database given above is for reference. In the real world there would be thousands of names and more complicated data for each one of them.

All that's left now is running the java code (CarRental.java) in any IDE and if everything is setup correctly it should work.
