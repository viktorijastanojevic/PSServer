/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package storage.baza;

import storage.Repozitorijum;
import storage.baza.konekcija.Konekcija;

/**
 *
 * @author Viktorija
 */
public interface DBRepozitorijum<T> extends Repozitorijum<T> {

    default void potvrdiTransakciju() throws Exception {
         Konekcija.getInstanca().getKonekcija().commit();

    }

    default void ugasiKonekciju() throws Exception {
        Konekcija.getInstanca().getKonekcija().close();

    }

    default void ponistiTransakciju() throws Exception {
        Konekcija.getInstanca().getKonekcija().rollback();

    }

    default void povezi() throws Exception {
        Konekcija.getInstanca().getKonekcija();

    }
}
