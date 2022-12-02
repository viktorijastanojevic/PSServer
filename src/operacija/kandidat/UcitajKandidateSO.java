/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kandidat;

import java.util.List;
import model.Kandidat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Viktorija
 */
public class UcitajKandidateSO extends ApstraktnaGenerickaOperacija {
    List<Kandidat> musterije;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        musterije = broker.vratiSve(new Kandidat(), "");
    }

    public List<Kandidat> getMusterije() {
        return musterije;
    }
}
