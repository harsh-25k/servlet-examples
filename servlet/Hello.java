package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<h1>Hello Humans</h1>");
		out.println();
		out.println("<a href='http://localhost:8080/event-1/idCount'>IdCount</a><br/>");
		out.println("<a href='http://localhost:8080/event-1/eventCount'>EventCount</a><br/>");
		out.println("<a href='http://localhost:8080/event-1/errorCountForm'>ErrorCount</a><br/>");
		out.println("<a href='http://localhost:8080/event-1/transactionCount'>Transactions</a><br/>");
		out.println("<a href='http://localhost:8080/event-1/compare'>Comparison</a><br/>");
		

		
	}
	
	
}
