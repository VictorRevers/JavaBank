package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Manager;
import banco_dados.*;



@WebServlet(name = "manager", urlPatterns = {"/manager"})
public class manager extends HttpServlet {
   conexaoBancoDados db = new conexaoBancoDados();
   ManagerTbl managerTbl = new ManagerTbl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.getRequestDispatcher("ManagerArea.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {            
                //Integer opt = Integer.parseInt(request.getParameter("opt"));
                boolean openConnection = db.openConnection();
                Manager manager = new Manager();
                manager.name = request.getParameter("name");
                manager.registration = request.getParameter("registration");
                manager.password = request.getParameter("password");
                
          
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
    }

}
