package banco_dados;

import classes.User;

import java.sql.*;

public class UserTbl {
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;
    
    public void configConnection(Connection conBanco) {this.conBanco = conBanco;}
    
    String strComandoSQL = "";
    
    public boolean insertUser(User user){                          
        try{
            strComandoSQL = "INSERT INTO users(name, cpf, password, balance) VALUES('"+user.name+"','"+user.cpf+"','"+user.password+"','0')";
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.executeUpdate();
            
            return true;          
        }catch(Exception erro){
            erro.printStackTrace();
            return false;
        }
    }
    
    public Integer checkUser(String cpf, String password){
        
        try{
            strComandoSQL = "SELECT * FROM users WHERE cpf="+cpf+"";
            psComando = conBanco.prepareStatement(strComandoSQL);
            rsRegistros = psComando.executeQuery();
            
            
            if(password != null){
                if(rsRegistros.next() && password.equals(rsRegistros.getString("password"))){
                    Integer id = rsRegistros.getInt("id"); //Integer.parseInt(rsRegistros.getInt("id")); 
                    //user.name = rsRegistros.getString("name");
                    //user.balance = Double.parseDouble(rsRegistros.getString("balance"));   
                    return id;
                }else{
                    return 0;
                }
            }else{
                if(rsRegistros.next() && cpf.equals(rsRegistros.getString("cpf"))){
                    Integer id = rsRegistros.getInt("id");
                    return id;
                }else{
                    return 0;
                }
            }
            
                    
        }catch(Exception e){
            System.out.println("Erro: "+ e);
            return -1;
        }
    }
    
    public User getUser(Integer id){
        User user = new User();
        try{
            strComandoSQL = "SELECT * FROM users WHERE id="+id+"";
            psComando = conBanco.prepareStatement(strComandoSQL);
            rsRegistros = psComando.executeQuery();
            
            rsRegistros.next();
            
            user.name = rsRegistros.getString("name");
            user.balance = Double.parseDouble(rsRegistros.getString("balance"));
            user.id = id;
            
            return user;
        }catch(Exception e){
            System.out.println("Erro: "+ e);
            return null;
        }
    }
    
    
    
}
