
package banco_dados;

import classes.Manager;

import java.sql.*;

public class ManagerTbl {
    private Connection dbConnection;
    private PreparedStatement ps;
    private ResultSet result;
    
    public void configConnection(Connection dbConnection){this.dbConnection = dbConnection;}
    
    
    String strSQLCommand = "";
    
    public boolean insertManager(Manager manager){
        try{
            strSQLCommand = "INSERT INTO managers (name, registration, password)"
                +" VALUES('"+manager.name+"','"+manager.registration+"','"+manager.password+"')";
            ps = dbConnection.prepareStatement(strSQLCommand);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.print("Erro: "+ e);
            return false;
        }
    }   
    
    
    
    
}
