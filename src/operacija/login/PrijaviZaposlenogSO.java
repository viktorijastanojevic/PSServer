/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import java.util.List;
import model.Zaposleni;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Viktorija
 */
public class PrijaviZaposlenogSO extends ApstraktnaGenerickaOperacija {
    Zaposleni zaposleni;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Zaposleni> sviZaposleni = broker.vratiSve((Zaposleni) param, null);
        Zaposleni z = (Zaposleni) param;
        if (sviZaposleni.contains(z)) {
            zaposleni = z;
        } else {
            throw new Exception("Zaposleni ne postoji");
        }

    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    
}
