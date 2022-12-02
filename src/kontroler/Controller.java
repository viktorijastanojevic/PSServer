/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import java.net.Socket;
import model.Zaposleni;
import operacija.login.PrijaviZaposlenogSO;
import server.PokretanjeServera;

/**
 *
 * @author Viktorija
 */
public class Controller {
    PokretanjeServera ps;
 
 
    private static Controller instance;

    private Controller() {
        
        
    }
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        return instance;
    }
    
    public void pokreniServer(){
        ps = new PokretanjeServera();
        ps.start();
        
       
    }
    
  

    public void zaustaviServer() {
        ps.zaustaviServer();
         
    }

    
    public PokretanjeServera getPs() {
        return ps;
    }

    public void setPs(PokretanjeServera ps) {
        this.ps=ps;
    }

    public Zaposleni login(String username, String password, Socket s) throws Exception {
    PrijaviZaposlenogSO operacija = new PrijaviZaposlenogSO();
        Zaposleni zaposleni = new Zaposleni("","",username, password, false);

        operacija.izvrsi(zaposleni, null);

        zaposleni = operacija.getZaposleni();
        return zaposleni;
    }
}
