package klasy;
// Generated 2013-05-31 18:05:26 by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Film generated by hbm2java
 */
public class Film implements java.io.Serializable {

    private int id;
    private String nazwa;
    private int kategoriaWiekowa;
    private int czasTrwania;
    private String opis;
    private Set<Seans> seanses = new HashSet(0);

    public Film() {
    }

    public Film(String nazwa, int kategoriaWiekowa, int czasTrwania) {
        //this.id = id;
        this.nazwa = nazwa;
        this.kategoriaWiekowa = kategoriaWiekowa;
        this.czasTrwania = czasTrwania;
    }

    public Film(String nazwa, int kategoriaWiekowa, int czasTrwania, String opis) {
        //this.id = id;
        this.nazwa = nazwa;
        this.kategoriaWiekowa = kategoriaWiekowa;
        this.czasTrwania = czasTrwania;
        this.opis = opis;
    }
    
    public Film(String nazwa, int kategoriaWiekowa, int czasTrwania, String opis, Set seanses) {
       this.nazwa = nazwa;
       this.kategoriaWiekowa = kategoriaWiekowa;
       this.czasTrwania = czasTrwania;
       this.opis = opis;
       this.seanses = seanses;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getKategoriaWiekowa() {
        return this.kategoriaWiekowa;
    }

    public void setKategoriaWiekowa(int kategoriaWiekowa) {
        this.kategoriaWiekowa = kategoriaWiekowa;
    }

    public int getCzasTrwania() {
        return this.czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public String getOpis() {
        return this.opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Set getSeanses() {
        return this.seanses;
    }

    public void setSeanses(Set seanses) {
        this.seanses = seanses;
    }
}
