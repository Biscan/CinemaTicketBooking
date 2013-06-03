/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import klasy.Film;

/**
 *
 * @author mateusz
 */
@ManagedBean
@SessionScoped
public class AdminLoginBean 
{
    String inputPass;
    String errMsg;
    boolean isLogged = false;
    /**
     * Creates a new instance of AdminLoginBean
     */
    
    public AdminLoginBean() 
    {
    }
    public String login()
    {
        System.out.println(inputPass);
        if(inputPass.equals("admin"))
        {
            isLogged = true;
            return "admin_site";
        }
            
        else
        {
            inputPass = "";
            isLogged = false;
            return "admin_index";
        }
    }

    public String getInputPass() {
        return inputPass;
    }

    public void setInputPass(String inputPass) {
        this.inputPass = inputPass;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
}
