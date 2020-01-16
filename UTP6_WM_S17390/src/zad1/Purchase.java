/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;


public class Purchase {
    String idKlienta;
    String nazwisko;
    String imie;
    String nazwaTowaru;
    double cena;
    double zakupionaIlosc;

    public Purchase(String idKlienta, String nazwisko, String imie, String nazwaTowaru, double cena, double zakupionaIlosc) {
        this.idKlienta = idKlienta;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.nazwaTowaru = nazwaTowaru;
        this.cena = cena;
        this.zakupionaIlosc = zakupionaIlosc;
    }

    public String getIdKlienta() {
        return idKlienta;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public double getCena() {
        return cena;
    }

    public double getZakupionaIlosc() {
        return zakupionaIlosc;
    }

    public Double getKoszty(){
        return getCena() * getZakupionaIlosc();
    }

    @Override
    public String toString() {
        return this.idKlienta + ";" + this.nazwisko + " " + this.imie + ";" + this.nazwaTowaru + ";" + this.cena + ";" + this.zakupionaIlosc;
    }
}
