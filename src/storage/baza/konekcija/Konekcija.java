/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage.baza.konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import konfiguracija.Konfiguracija;

/**
 *
 * @author Viktorija
 */
public class Konekcija {
    private static Konekcija instanca;
    private Connection konekcija;

    private Konekcija() throws SQLException {

    }

    public static Konekcija getInstanca() throws SQLException {
        if (instanca == null) {
            instanca = new Konekcija();
        }
        return instanca;
    }

    public Connection getKonekcija() throws SQLException {
        if (konekcija == null || konekcija.isClosed()) {
            String url = Konfiguracija.getInstanca().getProperty("url");
            String user = Konfiguracija.getInstanca().getProperty("user");
            String pass = Konfiguracija.getInstanca().getProperty("password");
            
            konekcija = DriverManager.getConnection(url, user, pass);
            konekcija.setAutoCommit(false);
        }
        return konekcija;
    }
}
