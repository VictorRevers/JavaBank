package servlets;

import classes.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Transfer", urlPatterns = {"/transfer"})
public class transfer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                double balance = Double.parseDouble(request.getParameter("b"));
                int origin = Integer.parseInt(request.getParameter("o"));
                int destination = Integer.parseInt(request.getParameter("d"));
                double value = Double.parseDouble(request.getParameter("v"));
        
                request.setAttribute("balance", balance);
                request.setAttribute("origin", origin);
                request.setAttribute("destination", destination);
                request.setAttribute("value", value);
                
                request.getRequestDispatcher("TransferProof.jsp").forward(request, response);      
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            User user = new User();
            user.balance = 2000.00;
            
            
            String origin = request.getParameter("origin"); 
            String destination = request.getParameter("acc");            
            double value = Double.parseDouble(request.getParameter("value"));
                    
            
            if(value > user.balance){
                request.setAttribute("neMoney", "Transferência cancelada! Saldo insuficiente!");
                request.getRequestDispatcher("Home.jsp").forward(request, response);
            }else{
                user.balance -= value;
                request.setAttribute("balance", user.balance);
                request.setAttribute("origin", origin);
                request.setAttribute("destination", destination);
                request.setAttribute("value", value);
                
                //request.getRequestDispatcher("TransferProof.jsp").forward(request, response); 
                response.sendRedirect("transfer?o="+origin+"&d="+destination+"&v="+value+"&b="+user.balance);              
            } 
    }
}