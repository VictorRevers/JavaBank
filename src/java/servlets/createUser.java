package servlets;
import classes.User;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "createUser", urlPatterns = {"/createUser"})
public class createUser extends HttpServlet {

    int totalIds = 1;  
    User dflUser = new User();
    ArrayList<User> users = dflUser.usersDbInit();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = new User();
                
                for(User u : users){
                    if(u.id == id){
                        user = u;
                        break;
                    }
                }
                
                request.setAttribute("id", user.id);
                request.setAttribute("name", user.name);
                request.setAttribute("balance", user.balance);
                
                request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
                      
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                                    
                User user = new User();
                user.cpf = "00000";
                               
                int formOpt = Integer.parseInt(request.getParameter("formOpt"));
                
                //LOGIN
                if(formOpt == 1){
                    String cpf = request.getParameter("cpf");                   
                    for(User u : users){
                        if(u.cpf.equals(cpf)){
                            user = u;
                            break;
                        }
                    }
                    if(!user.cpf.equals(cpf)){
                        request.setAttribute("msg", "Usuário ou senha incorretos!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }else{
                        String password = request.getParameter("password");
                        if(user.password.equals(password)){
                            response.sendRedirect("createUser?id="+user.id);                            
                        }else{
                            request.setAttribute("msg", "Usuário ou senha incorretos!");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }                
                }
                
                //CADASTRO
                if(formOpt == 2){
                    user.cpf = request.getParameter("cpf");
                    user.name = request.getParameter("name");
                    user.password = request.getParameter("password");
                    
                    for(User u : users){
                        if(u.cpf.equals(user.cpf)){
                            request.setAttribute("msg", "Este CPF já foi cadastrado!");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                            break;
                        }                                                 
                    }
                    user.id = totalIds;
                    user.balance = 2000.00;                  
                    users.add(user);
                    totalIds++;
                    response.sendRedirect("createUser?id="+user.id);
                }                                                         
    }
}
