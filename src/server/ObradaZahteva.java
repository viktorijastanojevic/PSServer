/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.util.Operacije;
import kontroler.Controller;
import model.Zaposleni;

/**
 *
 * @author Viktorija
 */
public class ObradaZahteva extends Thread{
    Socket s;
    
   
    
    public ObradaZahteva() {
    }

    public ObradaZahteva(Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        
            Primalac primalac = new Primalac(s);
            Posiljalac posiljalac = new Posiljalac(s);
            while (!s.isClosed() && s.isConnected()) {
                try {
                    Odgovor odgovor = new Odgovor();
                    Zahtev zahtev = (Zahtev) primalac.primi();
                    try {
                        switch (zahtev.getOperacija()) {
                            case LOGIN:
                                Zaposleni z = (Zaposleni) zahtev.getPodatak();
                                z = Controller.getInstance().login(z.getUsername(), z.getPassword(), s);
                                odgovor.setPodatak(z);
                                odgovor.setOperacija(Operacije.LOGIN);
                                odgovor.setGreska(null);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    posiljalac.posalji(odgovor);
                } catch (Exception ex) {
                    Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
           
    }
    }
    void prekini() {
        try {
            s.close();
            interrupt();
        } catch (IOException ex) {
//            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
