package servlets;
import classes.User;
import banco_dados.*;


import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

@WebServlet(name = "createUser", urlPatterns = {"/createUser"})
public class createUser extends HttpServlet {
        conexaoBancoDados db = new conexaoBancoDados();      
        UserTbl userTbl = new UserTbl();
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                boolean openConnection = db.openConnection();
                
                if(openConnection){
                    Integer id = Integer.parseInt(request.getParameter("id"));                                                            
                    userTbl.configConnection(db.getConnection());                   
                    User user = userTbl.getUser(id);
                    HttpSession session = request.getSession();
                    
                    
                    session.setAttribute("id", Integer.toString(user.id));
                    //session.setAttribute("name", user.name);
                    //session.setAttribute("balance", user.balance);                  
                    session.setMaxInactiveInterval(30*60);
                    
                    Cookie userName = new Cookie("name",user.name);
                    Cookie userBalance = new Cookie("balance", Double.toString(user.balance));
                    userName.setMaxAge(30*60);
                    userBalance.setMaxAge(30*60);
                    response.addCookie(userName);
                    response.addCookie(userBalance);
                    
                                                                                            
                    db.closeConnection();
                    
                    response.sendRedirect("Home.jsp");
                    
                    
                    
                            
                   //request.getRequestDispatcher("Home.jsp").forward(request, response);
                   
                }else{
                    db.closeConnection();
                    request.setAttribute("msg", "Ocorreu uma falha!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                
    }
                      
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                boolean openConnection = db.openConnection();                   
                User user = new User();
                                               
                int formOpt = Integer.parseInt(request.getParameter("formOpt"));
                
                //LOGIN
                if(formOpt == 1){
                    String cpf = request.getParameter("cpf");
                    String password = request.getParameter("password");                  
                    
                    if(openConnection){
                        userTbl.configConnection(db.getConnection());
                        
                        Integer checkUser = userTbl.checkUser(cpf, password);
                        
                        if(checkUser != 0 && checkUser != -1){
                            db.closeConnection();
                            response.sendRedirect("createUser?id="+checkUser);                          
                        }else if(checkUser == -1){
                            db.closeConnection();
                            request.setAttribute("msg", "Internal server error: 500!");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }else{
                            db.closeConnection();
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
                               
                   if(openConnection){
                       userTbl.configConnection(db.getConnection());
                       
                       Integer checkUser = userTbl.checkUser(user.cpf, null);
                       
                       if(checkUser == 0){
                           boolean insert =  userTbl.insertUser(user);
                            if(insert){
                               user.id = userTbl.checkUser(user.cpf, user.password);
                                db.closeConnection();
                                response.sendRedirect("createUser?id="+user.id);
                            }else{
                                db.closeConnection();
                                request.setAttribute("msg", "Erro ao cadastrar!");                     
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                       }else{
                            request.setAttribute("msg", "CPF já cadastrado!");                     
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                       }
                   }
                                                                                          
                }                                                        
    }
}
