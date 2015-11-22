package com.journaldev.first;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Utitlities {
	//metoda ³¹czy sie z baza danych i wypisuje pobrane dane w postaci elementow listy html
	public static String Write(){		
		try {
				Connection con;
			
				con = new DBconnection().getConnection();
				String query = "SELECT firstname, lastname, pesel, email, ocupationName FROM users LEFT JOIN ocupation ON users.ocupation = ocupation.id";
				if(con == null) System.out.println("no connection");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				String out = "";
				
				while(rs.next())
				{
					out = out+"<tr><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td><td>"+rs.getString("pesel")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getString("ocupationName") +" </tr>";
				}
					
				st.close();
				return out;
		} 
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
}
