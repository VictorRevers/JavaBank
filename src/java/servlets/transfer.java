package servlets;

import banco_dados.*;
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
        
            conexaoBancoDados db = new conexaoBancoDados();
            UserTbl userTbl = new UserTbl();
            boolean connectionOpen = db.openConnection();
            
            
            Integer origin = Integer.parseInt(request.getParameter("origin")); 
            Integer destination = Integer.parseInt(request.getParameter("acc"));            
            double value = Double.parseDouble(request.getParameter("value"));
            User user = new User();
            
            if(connectionOpen){
                userTbl.configConnection(db.getConnection());
                user = userTbl.getUser(origin);
            }
             
            
            if(value > user.balance){
                request.setAttribute("neMoney", "Transferência cancelada! Saldo insuficiente!");
                request.getRequestDispatcher("Home.jsp").forward(request, response);
            }else{
                if(connectionOpen){
                    userTbl.configConnection(db.getConnection());               
                    boolean transfer = userTbl.Transfer(user, destination, value);
                    
                    if(transfer){
                        db.closeConnection();
                        user.balance -= value;
                        response.sendRedirect("transfer?o="+origin+"&d="+destination+"&v="+value+"&b="+user.balance);                     
                    }else{
                        request.setAttribute("neMoney", "Transferência cancelada! Ocorreu uma falha!");
                        request.getRequestDispatcher("Home.jsp").forward(request, response);
                    }
                }
                
                /*request.setAttribute("balance", user.balance);
                request.setAttribute("origin", origin);
                request.setAttribute("destination", destination);
                request.setAttribute("value", value);*/
                
                //request.getRequestDispatcher("TransferProof.jsp").forward(request, response); 
                              
            } 
    }
}
