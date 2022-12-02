/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package storage;

import java.util.List;

/**
 *
 * @author Viktorija
 */
public interface Repozitorijum<T> {
    public List<T> vratiSve(T param, String join) throws Exception;
    public List<T> vratiPoKriterijumu(T param, String join, String uslov) throws Exception;
    public T vratiPoPrimarnomKljucu(T param, String join) throws Exception;
    public void ubaci(T param) throws Exception;
    public void obrisi(T param) throws Exception;
    public void izmeni(T param) throws Exception;
}
