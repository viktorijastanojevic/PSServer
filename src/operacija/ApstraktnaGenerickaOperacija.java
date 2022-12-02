/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import storage.Repozitorijum;
import storage.baza.DBRepozitorijum;
import storage.baza.RepozitorijumDBGenericki;

/**
 *
 * @author Viktorija
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        broker = new RepozitorijumDBGenericki();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
            ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DBRepozitorijum) broker).povezi();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).ponistiTransakciju();
    }

    private void ugasiKonekciju() throws Exception {
        ((DBRepozitorijum) broker).ugasiKonekciju();
    }
}
