package com.journaldev.first;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet1() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ErrorMessageBean message = new ErrorMessageBean();
		PersonBean person = new PersonBean();
		
		person.setName(request.getParameter("imie"));
		person.setSurname(request.getParameter("nazwisko"));
		person.setPesel(request.getParameter("pesel"));
		person.setEmail(request.getParameter("email"));
		person.setOcupation(Integer.parseInt(request.getParameter("zawod")));
		
		String errorMsg = "";
		boolean err = false;
		
		if(!nameValidation(person.getName())){
			errorMsg = errorMsg + "nie poprawne imie</br>";
			err = true;
		}
		if(!nameValidation(person.getSurname())){
			errorMsg = errorMsg + "nie poprawne nazwisko</br>";
			err = true;
		}
		if(!peselValidation(person.getPesel())){
			errorMsg = errorMsg + "nie poprawny pesel</br>";
			err = true;
		}
		if(!emailValidation(person.getEmail())){
			errorMsg = errorMsg + "nie poprawny email</br>";
			err = true;
		}
		if(err == false)
		{
			Connection con;
			try {
				con = new DBconnection().getConnection();
				String query = "INSERT INTO users ( firstname, lastname, pesel, email, ocupation) VALUES (?, ?, ? ,? ,? )" ;
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, request.getParameter("imie") ); 
				preparedStmt.setString(2, request.getParameter("nazwisko") ); 
				preparedStmt.setString(3, request.getParameter("pesel") ); 
				preparedStmt.setString(4, request.getParameter("email") ); 
				preparedStmt.setString(5, request.getParameter("zawod") ); 
				
				preparedStmt.execute();
				preparedStmt.close();
				//getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
							
			}catch (SQLIntegrityConstraintViolationException e){
				e.printStackTrace();	
				errorMsg = errorMsg + "zduplikowany pesel</br>";
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				errorMsg = errorMsg + "nie poprawne dane</br>";
				
			}
		}
		message.setMessage(errorMsg); 
		request.setAttribute("ErrorMessage", message);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);		
	}
	
	protected boolean nameValidation(String name){
		
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(name);
		boolean matchFound = m.matches();
		
		if(name.length() > 30)
			matchFound = false;
		if(matchFound){
			return true;
		}else
		return false;
	}
	
	protected boolean peselValidation(String pesel){
		Pattern p = Pattern.compile("[0-9]{4}[0-2][0-9][0-9]{5}");
		Pattern pp = Pattern.compile("[0-9]{4}[3][0-1][0-9]{5}");
		Matcher m = p.matcher(pesel);
		boolean matchFound = m.matches();
		if(!matchFound)
		{
			m = pp.matcher(pesel);
			matchFound = m.matches();
		}
		
		if(matchFound)
		{
			int[] peselInt = new int[10];
			for(int i = 0; i < 10; i++ )
			{
				peselInt[i] = Integer.valueOf(pesel.charAt(i)-48);
				System.out.println(peselInt[i]);
			}
			
			long peselControl = 
					1*peselInt[0] +	3*peselInt[1] + 7*peselInt[2] +
					9*peselInt[3] + 1*peselInt[4] +	3*peselInt[5] + 
					7*peselInt[6] +	9*peselInt[7] + 1*peselInt[8] +
					3*peselInt[9];
			
			System.out.println(peselControl);
			if (!(peselControl % 10 == 0 && Integer.valueOf(pesel.charAt(10)-48) == 0 ))
				if(!(Integer.valueOf(pesel.charAt(10)-48) == (10 - peselControl%10)))
					matchFound = false;
		}
		if(pesel.length() > 11)
			matchFound = false;
		if(matchFound)
		{
			return true;
		}else
		return false;
	}
	protected boolean emailValidation(String email)
	{
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		
		if(email.length() > 40)
			matchFound = false;
		
		if(matchFound)
		{
			return true;
		}else
		return false;
	}

}
