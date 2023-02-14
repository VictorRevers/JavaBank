package banco_dados;

import java.sql.*;

public class conexaoBancoDados {
    Connection conBanco;
    
    public boolean openConnection(){
        String url = "jdbc:mysql://localhost:3306/bankdb?user=root&password=senhadb&useSSL=false&allowMultiQueries=true";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conBanco = DriverManager.getConnection(url, "root", "senhadb");
            return true;
        }catch(Exception e){
            System.out.println("Erro " + e.toString());
            return false;         
        }
    }
    
    public boolean closeConnection(){
        try{
            conBanco.close();
            return true;
        }catch(SQLException erro){
            erro.printStackTrace();
            return false;
        }
    }
    
    public Connection getConnection() {return conBanco;}
    
}
