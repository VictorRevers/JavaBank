package servlets;

import banco_dados.UserTbl;
import banco_dados.conexaoBancoDados;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victo
 */
@WebServlet(name = "deposit", urlPatterns = {"/deposit"})
public class deposit extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                request.getRequestDispatcher("TransferProof.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String cpf = request.getParameter("cpf");
                double value = Double.parseDouble(request.getParameter("value"));
                String password = request.getParameter("password");
              
                conexaoBancoDados db = new conexaoBancoDados();
                UserTbl userTbl = new UserTbl();
              
                boolean connectionOpen =  db.openConnection();
                
                if(connectionOpen){
                    userTbl.configConnection(db.getConnection());
                    int deposit = userTbl.deposit(cpf, value);
                    
                    if(deposit == 0){
                        db.closeConnection();
                        request.setAttribute("msg", "Depósito cancelado: CPF informado não está cadastrado!");
                        request.getRequestDispatcher("ManagerArea.jsp").forward(request,response);                      
                    }else if(deposit == 1){
                        db.closeConnection();
                        response.sendRedirect("deposit");
                    }else if(deposit == -1){
                        db.closeConnection();
                        request.setAttribute("msg", "Depósito cancelado: Erro interno!");
                        request.getRequestDispatcher("ManagerArea.jsp").forward(request,response);
                    }
                }else{
                        request.setAttribute("msg", "Depósito cancelado: Falha ao conectar com banco!");
                        request.getRequestDispatcher("ManagerArea.jsp").forward(request,response);
                }
                
    }

    

}
