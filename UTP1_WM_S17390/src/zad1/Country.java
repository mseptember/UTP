package zad1;

public class Country {
    public String nazwa;
    public String stolica;
    public Integer populacja;

    public Country(String nazwa, String stolica, Integer populacja) {
        this.nazwa = nazwa;
        this.stolica = stolica;
        this.populacja = populacja;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getStolica() {
        return stolica;
    }

    public Integer getPopulacja() {
        return populacja;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setStolica(String stolica) {
        this.stolica = stolica;
    }

    public void setPopulacja(Integer populacja) {
        this.populacja = populacja;
    }
}
