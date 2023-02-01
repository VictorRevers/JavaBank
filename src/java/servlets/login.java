package servlets;
import classes.User;

import classes.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String pass = "123";
            String cpf = "12345";
            String name = "Usuário";
            
            User user = new User();
            user.cpf = request.getParameter("cpf");
            user.password = request.getParameter("password");
            
            if(user.cpf.equals(cpf) && user.password.equals(pass)){
                request.setAttribute("name", name);
                request.setAttribute("id", 1);
                request.setAttribute("balance", 2000.00);
            }else{
                //implementar
            }
            
            request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
}
