package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Browar {
    @Id
    private String name;
    private long wartosc;
    @OneToMany(mappedBy = "browar", cascade = CascadeType.REMOVE)
    private List<Piwo> piwos;

    public Browar(String name, long wartosc) {
        this.name = name;
        this.wartosc = wartosc;
        piwos = new ArrayList<>();
    }

    public Browar() {
        // Domyślny konstruktor bez argumentów
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Name: " + name + " wartosc: " + wartosc + " piwa: " + piwos;
    }

    public void addPiwo(Piwo piwo) {
        piwos.add(piwo);
    }
}