package com.journaldev.first;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	Connection con = null;
	public static void main(String[] args) throws Exception {
		
	}
		public Connection getConnection() throws Exception{
			try{
				System.out.println("hi");
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/bazamarcin";
				String username = "root";
				String pass = "";
				Class.forName(driver);
				
				con = DriverManager.getConnection(url,username,pass);
				System.out.println("Connected");
				return con;
			} catch(Exception e){System.out.println("halo "+e+" halo");}
			System.out.println("halo");
			return con;
		}
		
	}


