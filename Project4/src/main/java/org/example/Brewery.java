package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Brewery {
    @Id
    private String name;
    private long wartosc;
    @OneToMany(mappedBy = "brewery", cascade = CascadeType.REMOVE)
    private List<Beer> beers;

    public Brewery(String name, long wartosc) {
        this.name = name;
        this.wartosc = wartosc;
        beers = new ArrayList<>();
    }

    public Brewery() {
        // Domyślny konstruktor bez argumentów
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Nazwa: " + name + " wartosc: " + wartosc + " piwa: " + beers;
    }

    public void addPiwo(Beer beer) {
        beers.add(beer);
    }
}