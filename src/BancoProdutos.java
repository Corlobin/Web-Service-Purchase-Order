/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class BancoProdutos {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    
    public String getall()
    {
        String ret = "";
        for(int i = 0; i < produtos.size(); i++)
        {
            Produto prod = produtos.get(i);
            ret += prod.getDescricao()+";"+prod.getQuantidade()+";"+prod.getPreco()+"|";
        }
        return ret;
    }
    
    public int check(){
        TConsultaCompra thread = new TConsultaCompra();
        thread.start();
        return 1;
    }
    
    public int buy(String symbol) {
        for(int i = 0; i < produtos.size(); i++)
        {
            Produto prod = produtos.get(i);
            if( prod.getDescricao().equalsIgnoreCase(symbol))
            {                
                TPedidoCompra pedido = new TPedidoCompra();
                pedido.start();
                return 1;
            }            
        }
        return 0;
    }
 
    public int buylista(String prods) {
        
        int subtotal = 0;
        try
        {
            String[] compras = prods.split("\\|");
            for(int i = 0; i < compras.length; i++)
            {
                String[] produto = compras[i].split("\\;"); 
                String nome = produto[0];
                int quantidade = Integer.parseInt(produto[1]);
                
                for(int j = 0; j < produtos.size(); j++)
                {
                    Produto prod = produtos.get(i);
                    if( prod.getDescricao().equalsIgnoreCase(nome))
                    {
                        subtotal += prod.getPreco()*quantidade;
                    }                    
                }
            }
        
            PrintWriter print = new PrintWriter("C:\\pedido.txt");
            print.write(prods);
            print.close();
            
            TPedidoCompra thread = new TPedidoCompra();
            thread.start();
        
            return subtotal;
        }
        catch(Exception ex)
        {
        }
 
        return subtotal;
    }
 
    public String aceitarPedido(String resposta){
        try{
            PrintWriter print = new PrintWriter("C:\\resposta.txt");
            print.write("ACEITO");
            print.close();
        }
        catch(Exception ex){
            
        }
        return "OK";
    }
    
    
    public String get(String symbol)
    {
        for(int i = 0; i < produtos.size(); i++)
        {
            Produto prod = produtos.get(i);
            if( prod.getDescricao().equalsIgnoreCase(symbol))
            {
                return prod.toString();
            }
        }
        
        return null;
    }
    
    public int add(String symbol, double price) 
    {
        Produto produto = new Produto(symbol, 100, new Float(price));
        produtos.add(produto);
        
        return 1;
    }
    
    
}
