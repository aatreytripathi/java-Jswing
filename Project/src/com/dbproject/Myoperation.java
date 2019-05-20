package com.dbproject;

import java.sql.*;
public class Myoperation
{
	
private static Connection cn;
public static Connection createConnection()//Not a built-in method
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");//forName method creates object of Driver class in Memory
		//factory method
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
		//port no=3306 :- is a logical no to identify Database or Server
		//lookback address =127.0.0.1
//	System.out.println("Connected");
	}
	catch(SQLException|ClassNotFoundException cse) 
	{
		System.out.println(cse);
	}
	return cn;
}
/*public static void main(String[] args) 
{
	createConnection();
}
*/}
