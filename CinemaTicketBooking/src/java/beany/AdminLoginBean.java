/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mateusz
 */
@ManagedBean
@SessionScoped
public class AdminLoginBean 
{
    String inputPass;
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
    public String logout()
    {
        inputPass = "";
        isLogged = false;
        return "admin_index";
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
