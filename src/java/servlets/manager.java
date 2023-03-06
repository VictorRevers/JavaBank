package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import classes.Manager;
import classes.Encrypt;
import classes.User;
import banco_dados.*;




@WebServlet(name = "manager", urlPatterns = {"/manager"})
public class manager extends HttpServlet {
   conexaoBancoDados db = new conexaoBancoDados();
   ManagerTbl managerTbl = new ManagerTbl();
   Encrypt encrypt = new Encrypt();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();
                String sessionRegistration = (String)session.getAttribute("reg");
                
                if(sessionRegistration != null){
                    response.sendRedirect("ManagerArea.jsp");
                }else{
                    String registration = request.getParameter("reg");
                    String name = (String)request.getParameter("name");
                                                  
                
                    session.setAttribute("reg", registration);
                
                    session.setAttribute("active", true);
                    session.setMaxInactiveInterval(30*60);
                
                    Cookie mngrName = new Cookie("name", name);
                    Cookie mngrReg = new Cookie("reg", registration);
                    mngrName.setMaxAge(30*60);
                    mngrReg.setMaxAge(30*60);
                    response.addCookie(mngrName);
                    response.addCookie(mngrReg);                             
                            
                    response.sendRedirect("ManagerArea.jsp");
                }
                                              
                //request.getRequestDispatcher("ManagerArea.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {                          
                //Integer opt = Integer.parseInt(request.getParameter("opt"));
                boolean openConnection = db.openConnection();
                Manager manager = new Manager();
                String login = request.getParameter("loginManager");
                
                //LOGIN MANAGER
                if(login != null){
                   
                    String password = encrypt.encrypt(request.getParameter("password"));
                    String registration = request.getParameter("registration");
                   
                    if(openConnection){
                         managerTbl.configConnection(db.getConnection());
                         manager = managerTbl.getManager(registration, password);
                         
                         if(manager != null){
                            db.closeConnection();
                            response.sendRedirect("manager?reg="+registration+"&name="+manager.name);
                         }else{
                            db.closeConnection();
                            request.setAttribute("msg", "Matricula ou senha incorretos!");
                            request.getRequestDispatcher("LoginAdmin.jsp").forward(request, response);
                         }
                    }else{
                        db.closeConnection();
                        request.setAttribute("msg", "Ocorreu uma falha!");
                        request.getRequestDispatcher("LoginAdmin.jsp").forward(request, response);
                    }
                                                     
                }else{                   
                    HttpSession session =request.getSession();
                    String registration = (String)session.getAttribute("reg");
                                     
                    
                    if(registration != null){
                        manager.name = request.getParameter("name");
                        manager.registration = request.getParameter("registration");
                        manager.password = encrypt.encrypt(request.getParameter("password"));
                
          
                        //CADASTRAR GERENTE:
                        if(openConnection){
                            managerTbl.configConnection(db.getConnection());
            
                            //if(checkManager == 0){}
            
                            boolean insert = managerTbl.insertManager(manager);
                
                            if(insert){
                                db.closeConnection();
                                response.sendRedirect("manager");
                            }else{
                                db.closeConnection();
                                request.setAttribute("msg", "Erro ao cadastrar!");                     
                                request.getRequestDispatcher("ManagerArea.jsp").forward(request, response);
                            }
            
                        }
                    }else{
                        response.sendRedirect("LoginAdmin.jsp");
                    }                                                                                           
                }                                                                   
    }

}
