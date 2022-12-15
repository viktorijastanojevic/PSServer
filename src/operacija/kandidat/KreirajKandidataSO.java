/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kandidat;

import model.Kandidat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Viktorija
 */
public class KreirajKandidataSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Kandidat)) {
            throw new Exception("Sistem nije dobio kandidata");
        }

        Kandidat k = (Kandidat) param;
        if (k.getBrojTelefona() == null || k.getBrojTelefona().equals("")) {
            throw new Exception("Prazan telefon");
        }

        if (k.getIme() == null || k.getIme().equals("")) {
            throw new Exception("Prazno ime");
        }

        if (k.getPrezime() == null || k.getPrezime().equals("")) {
            throw new Exception("Prazno prezime");
        }
        
        if (k.getAdresa() == null || k.getAdresa().equals("")) {
            throw new Exception("Prazna adresa");
        }
 
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.ubaci((Kandidat) param);
    }
}
