
package banco_dados;

import classes.Manager;

import java.sql.*;

public class ManagerTbl {
    private Connection dbConnection;
    private PreparedStatement ps;
    private ResultSet result;
    private Manager manager = new Manager();
    
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
    
    /*public String checkManager(String registration, String password){
        try{
            strSQLCommand = "SELECT * FROM managers WHERE registration="+registration+"";
            ps = dbConnection.prepareStatement(strSQLCommand);
            result = ps.executeQuery();
            
            if(result.next() && password.equals(result.getString("password"))){
                String managerRegistration = registration; 
                return managerRegistration;
            }else{
                return null;
            }                            
        }catch(Exception e ){
            System.out.println("Erro: "+e);
            return null;
        }
    }*/
    
    public Manager getManager(String registration, String password){
        try{
            strSQLCommand = "SELECT * FROM managers WHERE registration="+registration+"";
            ps = dbConnection.prepareStatement(strSQLCommand);
            result = ps.executeQuery();
            
            if(result.next() && password.equals(result.getString("password"))){
                manager.name = result.getString("name");
                manager.registration = registration;
                return manager;
            }else{
                return null;
            }                            
        }catch(Exception e ){
            System.out.println("Erro: "+e);
            return null;
        }
       
    }
    
    
    
    
}
