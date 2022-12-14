/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.util.Operacije;
import kontroler.Controller;
import model.Kandidat;
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
                            case UCITAJ_KANDIDATE:
                                List<Kandidat> musterije = Controller.getInstance().ucitajMusterije();
                                odgovor.setPodatak(musterije);
                                odgovor.setOperacija(Operacije.UCITAJ_KANDIDATE);
                                odgovor.setGreska(null);
                                break;
                            case ZAPAMTI_KANDIDATA:
                                Controller.getInstance().kreiraj((Kandidat) zahtev.getPodatak());
                                odgovor.setOperacija(Operacije.ZAPAMTI_KANDIDATA);
                                odgovor.setGreska(null);
                                odgovor.setPodatak(null);
                            break;
                            case OBRISI_KANDIDATA:
                                Controller.getInstance().obrisi((Kandidat) zahtev.getPodatak());
                                odgovor.setOperacija(Operacije.OBRISI_KANDIDATA);
                                odgovor.setGreska(null);
                                odgovor.setPodatak(null);
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
