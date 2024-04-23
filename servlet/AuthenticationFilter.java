package com.servlet;
 
import javax.annotation.Priority;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Base64;
 
//@Priority(2)
@WebFilter(urlPatterns ={"/*"})
// User class to represent user data
 
 
// Filter to handle authentication
public class AuthenticationFilter implements Filter {
	@Override
    public void init(FilterConfig config) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        res.getWriter().print("yes filter is working");
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String[] credentials = new String(Base64.getDecoder().decode(authHeader.substring(6))).split(":");
        String email = credentials[0];
        String password = credentials[1];
        if(email.equals("saurabh@gmail.com") && password.equals("password")) {
        	res.getWriter().print("Yes we know YOU");
        }
        else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          res.getWriter().print("Not authorized");
          return;
        }
        chain.doFilter(req,res);
        // Extracting and decoding email and password from Authorization header
 

}
        // If authentication succeeds, proceed with the reques

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
// Example servlet
//public class ProtectedServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Access authenticated user
//        User authenticatedUser = (User) req.getAttribute("authenticatedUser");
//        if (authenticatedUser != null) {
//            resp.getWriter().write("Hello, " + authenticatedUser.email + "! You are authenticated.");
//        } else {
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//    }
//}