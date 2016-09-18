

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Soundbank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ricardo
 */
public class TPedidoCompra {    
    public void start(){
        String arquivo = "C:\\resposta.txt";
        FileChangeListener fcl = new FileChangeListener(arquivo);
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
