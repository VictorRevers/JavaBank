/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;


public class Encrypt {
    public String encrypt(String password) throws UnsupportedEncodingException{
        
        try{
            MessageDigest dg = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = dg.digest(password.getBytes("UTF-8"));
            StringBuilder hashPass = new StringBuilder();
        
            for(byte b : messageDigest){
                hashPass.append(b);
            }
        
            String strHash = hashPass.toString();
        
        
            return strHash;
        }catch(NoSuchAlgorithmException e){
            System.out.println("Error: "+e);
            return null;
        }
        
        
    }
}
