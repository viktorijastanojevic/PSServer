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
public class ObrisiKandidataSO extends ApstraktnaGenerickaOperacija{
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Kandidat)) {
            throw new Exception("Sistem nije dobio kandidata");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.obrisi((Kandidat) param);
    }
}
