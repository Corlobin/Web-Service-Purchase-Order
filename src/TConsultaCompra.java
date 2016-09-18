
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ricardo
 */
public class TConsultaCompra {
    public void start(){
        String arquivo = "C:\\pedido.txt";
        FileChangeListener fcl = new FileChangeListener("C:\\pedido.txt");
        try 
        {
            fcl.startListening(); 
            synchronized (fcl) {
                fcl.wait();
            }    
 
        }        
        catch (Exception ex) 
        {
        } 
        
    }
    
}
