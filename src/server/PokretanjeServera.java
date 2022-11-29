/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Viktorija
 */
public class PokretanjeServera extends Thread {
    boolean kraj=false;
      ServerSocket ss;
       List<ObradaZahteva> klijenti;
    public PokretanjeServera( ) {
       klijenti= new ArrayList<>();
        
    }
    
    @Override
    public void run() { try { 
       ss = new ServerSocket(9000);
    
        while(!kraj){
              System.out.println("Server je ukljucen, ceka se konekcija...");
        
            Socket s = ss.accept(); //cekamo dok ne dodje klijent 
            System.out.println("Klijent se povezao");
            //ovaj kod nije dovoljan, moramo negde da instanciramo nit, to cemo uraditi u Serverskoj formi u konstruktoru
            ObradaZahteva o = new ObradaZahteva(s); //nit koja obradjuje klijenta koji se povezao, za svakog klijenta odvojena nit
            klijenti.add(o);
            o.start(); 
            
            
             
        }
        } catch (IOException ex) {
             // sf.nijepokrenut();
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }
    public void zaustaviServer(){
         try {
             
             for (ObradaZahteva o : klijenti) {
                 o.prekini();
             }
             ss.close();
             kraj=true;
         } catch (IOException ex) {
             Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
