package com.profile_maker.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.profile_maker.models.Registration;
import com.profile_maker.services.RegistrationService;

@WebServlet(urlPatterns = "/RegistrationServlet", name = "RegistrationServlet", displayName = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 4154837325349194966L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Registration registrationVO = null;
			if(!request.getParameter("username").isEmpty() && !request.getParameter("password").isEmpty() && !request.getParameter("email_id").isEmpty() && !request.getParameter("phone").isEmpty()) {
				registrationVO = new Registration(request.getParameter("username"),request.getParameter("password"), request.getParameter("email"),Long.parseLong(request.getParameter("phone")));
				//check database for username and password 
				RegistrationService registrationService = new RegistrationService();
				boolean fetch_register = registrationService.fetch_register(registrationVO);
				if(fetch_register==true) {
					out.print("<h1> User Already exists with this Id</h1>");
					out.print("<h3> <a href ='index.html'> Go to Homepage </a> </h3>");
				}else {
					int register = registrationService.register(registrationVO);
					if(register==0) {
						out.print("<h1> Hello " + registrationVO.getUsername() + " registration is unsuccessfull.......</h1>");
						out.print("<h3> <a href ='html/Registration.html'> Sign Up </a> </h3>");
					}else {
						out.print("<h1> Hello " + registrationVO.getUsername() + " registration is successfull.......</h1>");
						out.print("<h3> <a href ='html/Login.html'> Sign In </a> </h3>");
					}
				}
			} else if (request.getParameter("username").isEmpty() || request.getParameter("username").length() < 6) {
				out.print("<h1> Username length should be atleast 6 ...</h1>");
			} else if (request.getParameter("password").isEmpty() || request.getParameter("password").length() < 8) {
				out.print("<h1> Password length should be atleast 8 ...</h1>");
			} else if (request.getParameter("email").isEmpty() || !request.getParameter("email").contains("@gmail.com")) {
				out.print("<h1> Only Gmail is allowed ....</h1>");
			} else if (request.getParameter("phone").isEmpty() || request.getParameter("password").length() < 10) {
				out.print("<h1> Mobile Number length should be 10 ...</h1>");
			}
		}catch(Exception e) {
			     
		}
	}
}
